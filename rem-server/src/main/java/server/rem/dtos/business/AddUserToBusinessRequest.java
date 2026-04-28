package server.rem.dtos.business;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AddUserToBusinessRequest {
    @NotBlank(message = "Fullname is required")
    @Size(max = 100)
    private final String fullname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100)
    private final String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private final String password;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private final String confirmPassword;

    private final String roleId;

    private final Boolean isActive;

    private final Boolean isVerified;

    private final LocalDate birthday;

    @Size(max = 10)
    private final String phone;

    private final String userId;

    // private String socialSecurity;

    // private Pronoun pronoun;

    // private Boolean gender;

    private final String bankOwner;

    private final String bankAccount;

    private final String bankName;

    private final String bankCode;

    private final String bankBranch;

    // private String taxCode;

    // private String departmentId;

    // private String mainBranchId;

    @NotNull(message = "Salary is required")
    private final Integer salary;

    private final Integer dependants;

    // private List<Integer> salaryExpensesIds;
}