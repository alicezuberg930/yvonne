package server.rem.dtos.auth;

import jakarta.validation.constraints.*;
import lombok.*;
import server.rem.enums.Provider;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpUserRequest {
    @NotBlank(message = "Fullname is required")
    @Size(max = 100)
    private String fullname;

    @Size(max = 10)
    private String phone;

    // Optional
    @Size(max = 255)
    private String avatar;

    // Optional (default can be handled in service)
    private Provider provider;

    // Optional
    private LocalDate birthday;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
