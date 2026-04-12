package server.rem.services;

import lombok.RequiredArgsConstructor;

import org.jspecify.annotations.Nullable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server.rem.dtos.business.AddUserToBusinessDto;
import server.rem.dtos.business.BusinessResponse;
import server.rem.dtos.business.CreateBusinessDto;
import server.rem.dtos.business.UpdateBusinessDto;
import server.rem.entities.Business;
import server.rem.entities.BusinessUser;
import server.rem.entities.BusinessUserId;
import server.rem.entities.Role;
import server.rem.entities.User;
import server.rem.mappers.AddUserToBusinessMapper;
import server.rem.mappers.BusinessMapper;
import server.rem.repositories.BusinessRepository;
import server.rem.repositories.BusinessUserRepository;
import server.rem.repositories.RoleRepository;
import server.rem.repositories.UserRepository;
import server.rem.utils.exceptions.ResourceNotFoundException;
import server.rem.utils.exceptions.UnauthorizedException;
import server.rem.utils.mail.DynamicMail;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessUserRepository businessUserRepository;
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final BusinessMapper businessMapper;
    private final DynamicMail dynamicMail;
    private final AddUserToBusinessMapper addUserToBusinessMapper;
    private final RoleRepository roleRepository;

    public List<BusinessResponse> getAllBusinesses(@Nullable String userId) {
        List<Business> businesses = List.of();
        if (userId != null) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) businesses = businessRepository.findAllByOwner(user.get());
        } else {
            businesses = businessRepository.findAll();
        }
        return businessMapper.toBusinessesResponse(businesses);
    }

    public Business createBusinesses(String userId, CreateBusinessDto dto) {
        if (userId == null) throw new UnauthorizedException("User id is required");
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Business business = businessMapper.toEntity(dto);
        business.setOwner(user);
        return businessRepository.save(business);
    }

    @Transactional
    public User addUserToBusiness(String invitorId, AddUserToBusinessDto dto, String businessId) throws Exception {
        if(businessUserRepository.findByUserEmailAndBusinessId(dto.getEmail(), businessId).isPresent())
            throw new RuntimeException("User is already a member of this business");
        Business business = businessRepository.findById(businessId).orElseThrow(() -> new RuntimeException("Business not found"));
        Role role = roleRepository.findById(dto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        User user;
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            user = userRepository.findByEmail(dto.getEmail()).get();
        } else {
            User createdUser = addUserToBusinessMapper.toUserEntity(dto);
            createdUser.setPassword(passwordEncoder.encode(createdUser.getPassword()));
            user = userRepository.save(createdUser);
        }
        BusinessUser businessUser = addUserToBusinessMapper.toBusinessUserEntity(dto, business, role);
        businessUser.setId(new BusinessUserId(businessId, user.getId()));
        // Set invitor if invitorId is provided and valid
        userRepository.findById(invitorId).ifPresent(invitor -> {
            businessUser.setInvitor(invitor);
        });

        businessUserRepository.save(businessUser);
        // send email to user with verify token
        ClassPathResource resource = new ClassPathResource("templates/verify-email.html");
        String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        html = html.replace("{{fullname}}", user.getFullname());
        html = html.replace("{{verifyToken}}", user.getVerifyToken());
        html = html.replace("http://yourdomain.com", "https://yourrealdomain.com");

        emailService.sendMail(businessId, user.getEmail(), "Verify your email", html);
        return user;
    }

    public Business updateBusiness(String businessId, UpdateBusinessDto dto) {
        Business business = businessRepository.findById(businessId).orElseThrow(() -> new ResourceNotFoundException("No business found"));
        businessMapper.updateEntity(dto, business);
        dynamicMail.updateMailStrategy(business);
        return businessRepository.save(business);
    }
}