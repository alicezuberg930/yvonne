package server.rem.dtos.business;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddUserToBusinessDto {
    @NotBlank(message = "Fullname is required")
    @Size(max = 100)
    private String fullname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String confirmPassword;

    private String roleId;

    private Boolean isActive;

    private Boolean isVerified;

    private LocalDate birthday;

    @Size(max = 10)
    private String phone;

    private String userId;

//    private String socialSecurity;

//    private Pronoun pronoun;

//    private Boolean gender;

    private String bankOwner;

    private String bankAccount;

    private String bankName;

    private String bankCode;

    private String bankBranch;

//    private String taxCode;

//    private String departmentId;

//    private String mainBranchId;

    @NotNull(message = "Salary is required")
    private Integer salary;

    private Integer dependants;

//    private List<Integer> salaryExpensesIds;
}
