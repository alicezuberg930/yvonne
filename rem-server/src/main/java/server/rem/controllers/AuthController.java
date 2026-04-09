package server.rem.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.rem.annotations.RequestUser;
import server.rem.dtos.APIResponse;
import server.rem.dtos.users.SignInUserDto;
import server.rem.dtos.users.SignUpUserDto;
import server.rem.entities.User;
import server.rem.services.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    
    @Value("${jwt.expiration}")
    private String jwtExpirationInS;

    @PostMapping("/sign-in")
    public ResponseEntity<APIResponse<String>> signIn(@Valid @RequestBody SignInUserDto dto, HttpServletResponse response) {
        String token = authService.signIn(dto);
        ResponseCookie cookie = ResponseCookie
                .from("ACCESS_TOKEN", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofSeconds(Long.parseLong(jwtExpirationInS)))
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok().body(APIResponse.success(
                200,
                "User signed in successfully",
                token
            )
        );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<APIResponse<SignUpUserDto>> signUp(@Valid @RequestBody SignUpUserDto dto) {
        return ResponseEntity.status(201).body(APIResponse.success(
                201,
                "User signed up successfully",
                authService.signUp(dto)));
    }

    @GetMapping("/me")
    public ResponseEntity<APIResponse<User>> profile(@RequestUser String userId) {
        return ResponseEntity.status(200).body(APIResponse.success(
                200,
                "Profile fetched successfully",
                authService.profile(userId)));
    }
}