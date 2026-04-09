package server.rem.dtos.users;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInUserDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}