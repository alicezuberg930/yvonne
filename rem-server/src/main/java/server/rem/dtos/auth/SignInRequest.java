package server.rem.dtos.auth;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@AllArgsConstructor
public class SignInRequest {
    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;
}