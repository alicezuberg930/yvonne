package server.rem.dtos.auth;

import jakarta.validation.constraints.*;
import lombok.*;
import server.rem.enums.Provider;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "Fullname is required")
    @Size(max = 100)
    private final String fullname;

    @Size(max = 10)
    private final String phone;

    // Optional
    @Size(max = 255)
    private final String avatar;

    // Optional (default can be handled in service)
    private final Provider provider;

    // Optional
    private final LocalDate birthday;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100)
    private final String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private final String password;
}
