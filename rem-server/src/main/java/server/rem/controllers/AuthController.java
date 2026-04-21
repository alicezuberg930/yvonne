package server.rem.controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import server.rem.annotations.RequestUser;
import server.rem.common.messages.AuthMessages;
import server.rem.dtos.APIResponse;
import server.rem.dtos.auth.RoleResponse;
import server.rem.dtos.auth.SignInUserRequest;
import server.rem.dtos.auth.SignInUserResponse;
import server.rem.dtos.auth.SignUpUserRequest;
import server.rem.dtos.auth.UserProfileResponse;
import server.rem.services.AuthService;
import server.rem.views.Views;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Value("${jwt.expiration}")
    private String expireIn;

    @PostMapping("/sign-in")
    public ResponseEntity<APIResponse<SignInUserResponse>> signIn(@Valid @RequestBody SignInUserRequest dto, HttpServletResponse response) {
        SignInUserResponse signInResponse = authService.signIn(dto);
        ResponseCookie cookie = ResponseCookie
                .from("ACCESS_TOKEN", signInResponse.getAccessToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofSeconds(Long.parseLong(expireIn)))
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok().body(APIResponse.success(
                200,
                AuthMessages.LOGIN_SUCCESS(signInResponse.getUser().getFullname()),
                signInResponse));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<APIResponse<SignUpUserRequest>> signUp(@Valid @RequestBody SignUpUserRequest dto) {
        return ResponseEntity.status(201).body(APIResponse.success(
                201,
                "User signed up successfully",
                authService.signUp(dto)));
    }

    @JsonView(Views.Public.class)
    @GetMapping("/me")
    public ResponseEntity<APIResponse<UserProfileResponse>> profile(@RequestUser String userId) {
        return ResponseEntity.status(200).body(APIResponse.success(
                200,
                "Profile fetched successfully",
                authService.profile(userId)));
    }

    @JsonView(Views.Public.class)
    @GetMapping("/role")
    public ResponseEntity<APIResponse<RoleResponse>> getCurrentRole(@RequestUser String userId,
            @RequestParam("businessId") String businessId) {
        return ResponseEntity.status(200).body(APIResponse.success(
                200,
                "Role fetched successfully",
                authService.getCurrentRole(userId, businessId)));
    }
}