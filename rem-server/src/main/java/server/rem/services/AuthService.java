package server.rem.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import server.rem.dtos.users.SignInUserDto;
import server.rem.dtos.users.SignUpUserDto;
import server.rem.entities.User;
import server.rem.enums.JWTAlgorithm;
import server.rem.mappers.UserMapper;
import server.rem.repositories.UserRepository;
import server.rem.utils.JWT;
import server.rem.utils.JWTOptions;
import server.rem.utils.exceptions.ResourceNotFoundException;
import server.rem.utils.exceptions.UnauthorizedException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

@Service
public class AuthService {
    @Value("${jwt.secret}")
    private String secret;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public String signIn(SignInUserDto dto) {
        try {
            JWT jwt = new JWT(secret, JWTAlgorithm.HS256);

            JWTOptions options = new JWTOptions();
            options.setExpiresIn(86400); // 1 day
            // options.setIssuer("my-app");
            // options.setIncludeIssuedTimestamp(true);
            User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

            boolean isMatch = passwordEncoder.matches(dto.getPassword(), user.getPassword());

            if (!isMatch) {
                throw new UnauthorizedException("Invalid credentials");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> claims = objectMapper.convertValue(dto, new TypeReference<Map<String, Object>>() {});
            claims.put("userId", user.getId());

            return jwt.sign(claims);
            // Verify
            // Map<String, Object> decoded = jwt.verify(token);
            // System.out.println(decoded.get("userId")); // 123
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public SignUpUserDto signUp(SignUpUserDto dto) {
        try {
            User user = UserMapper.toEntity(dto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public User profile(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Your profile doesn't exist"));
    }
}
