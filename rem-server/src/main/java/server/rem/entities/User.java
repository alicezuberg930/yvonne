package server.rem.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import server.rem.enums.Provider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Base {
    @Column(name = "fullname", length = 100, nullable = false)
    private String fullname;

    @Column(name = "phone", length = 20, unique = true)
    private String phone;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    @Builder.Default
    private Provider provider = Provider.LOCAL;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "is_verified", nullable = false)
    @Builder.Default
    private Boolean isVerified = false;

    @Column(name = "verify_token", length = 255)
    private String verifyToken;

    @Column(name = "verify_token_expires")
    private LocalDateTime verifyTokenExpires;

    @Column(name = "reset_password_token", length = 255)
    private String resetPasswordToken;

    @Column(name = "reset_password_expires")
    private LocalDateTime resetPasswordExpires;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @JsonManagedReference
    @JsonIgnoreProperties({ "owner", "users", "businessUsers" })
    private List<Business> businessesOwned;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "user", "invitor" })
    private List<BusinessUser> businessUsers;

    // @OneToMany(mappedBy = "invitor")
    // private List<BusinessUser> sentInvitations;
}