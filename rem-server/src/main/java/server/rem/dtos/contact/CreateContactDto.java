package server.rem.dtos.contact;
 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import server.rem.enums.ContactType;
 
import java.time.LocalDate;
 
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateContactDto {
    @NotNull(message = "Business ID is required")
    private String businessId;
 
    private String customerGroupId;
 
    @NotNull(message = "Tag ID is required")
    private String tagId;
 
    @NotNull(message = "Contact type is required")
    private ContactType type;
 
    @NotBlank(message = "First name is required")
    private String firstName;
 
    @NotBlank(message = "Last name is required")
    private String lastName;
 
    @NotBlank(message = "Surname is required")
    private String surname;
 
    @NotBlank(message = "Phone is required")
    private String phone;
 
    private String mobilePhone;
 
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
 
    private String birthday;
 
    private String occupation;
 
    @NotBlank(message = "Tax code is required")
    private String taxCode;
 
    private String website;
    private String facebook;
    private String instagram;
    private String zalo;
    private String identityCard;
    private LocalDate identityIssuedOn;
    private String identityIssuedAt;
    private String insuranceNumber;
    private String note;
    private String address1;
    private String address2;
    private String country;
    private String zipCode;
}
