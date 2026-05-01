package server.rem.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "business_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "invitor" })
@Builder
public class BusinessUser {
    @EmbeddedId
    private BusinessUserId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("businessId") // maps to BusinessUserId.businessId
    @JoinColumn(name = "business_id", nullable = false)
    @JsonIgnoreProperties({ "owner", "businessUsers", "businessesOwned" })
    private Business business;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId") // maps to BusinessUserId.userId
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invited_user_id", nullable = true)
    private User invitor;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "is_verified", nullable = false)
    @Builder.Default
    private Boolean isVerified = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "salary", nullable = true)
    private Integer salary;

    @Column(name = "bank_owner", nullable = true)
    private String bankOwner;

    @Column(name = "bank_account", nullable = true)
    private String bankAccount;

    @Column(name = "bank_name", nullable = true)
    private String bankName;

    @Column(name = "bank_code", nullable = true)
    private String bankCode;

    @Column(name = "bank_branch", nullable = true)
    private String bankBranch;

    @Column(name = "dependants", nullable = false)
    @Builder.Default
    private Integer dependants = 0;
}