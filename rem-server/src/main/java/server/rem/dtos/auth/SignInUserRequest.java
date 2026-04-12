package server.rem.dtos.auth;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInUserRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}