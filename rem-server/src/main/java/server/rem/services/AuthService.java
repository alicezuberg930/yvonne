package server.rem.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import server.rem.dtos.auth.RoleResponse;
import server.rem.dtos.auth.SignInUserRequest;
import server.rem.dtos.auth.SignInUserResponse;
import server.rem.dtos.auth.SignUpUserRequest;
import server.rem.dtos.auth.UserProfileResponse;
import server.rem.entities.BusinessUser;
import server.rem.entities.User;
import server.rem.enums.JWTAlgorithm;
import server.rem.mappers.AuthMapper;
import server.rem.mappers.UserMapper;
import server.rem.repositories.UserRepository;
import server.rem.utils.JWT;
import server.rem.utils.JWTOptions;
import server.rem.utils.exceptions.ResourceNotFoundException;
import server.rem.utils.exceptions.UnauthorizedException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Value("${jwt.secret}")
    private String secret;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final AuthMapper authMapper;
    @Value("${jwt.expiration}")
    private String jwtExpirationInS;
    private final EmailService emailService;

    public RoleResponse getCurrentRole(String userId, String businessId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        BusinessUser businessUser = user
                .getBusinessUsers()
                .stream()
                .filter(bu -> bu.getBusiness().getId().equals(businessId))
                .findFirst()
                .orElseThrow(() -> new UnauthorizedException("User is not authorized to view this business"));
        return authMapper.toRoleResponse(businessUser.getRole());
    }

    public SignInUserResponse signIn(SignInUserRequest dto) {
        try {
            JWTOptions options = new JWTOptions();
            options.setExpiresIn(Long.parseLong(jwtExpirationInS));
            JWT jwt = new JWT(secret, JWTAlgorithm.HS256);
            // options.setIssuer("my-app");
            // options.setIncludeIssuedTimestamp(true);
            User user = userRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

            boolean isMatch = passwordEncoder.matches(dto.getPassword(), user.getPassword());

            if (!isMatch)
                throw new UnauthorizedException("Invalid credentials");

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> claims = objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {
            });
            claims.put("userId", user.getId());

            String accessToken = jwt.sign(claims, options);
            return authMapper.toResponse(user, accessToken);
            // Verify
            // Map<String, Object> decoded = jwt.verify(token);
            // System.out.println(decoded.get("userId")); // 123
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public UserProfileResponse signUp(SignUpUserRequest dto) {
        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // send email to user with verify token
        // ClassPathResource resource = new ClassPathResource("templates/register-email.html");
        // String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        // html = html.replace("{{fullname}}", user.getFullname());
        // html = html.replace("{{verifyToken}}", user.getVerifyToken());
        // html = html.replace("http://yourdomain.com", "https://yourrealdomain.com");
        // emailService.sendMail(businessId, user.getEmail(), "Verify your email", html);
        return authMapper.toSummaryResponse(userRepository.save(user));
    }

    public UserProfileResponse profile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Your profile doesn't exist"));
        return authMapper.toSummaryResponse(user);
    }
}