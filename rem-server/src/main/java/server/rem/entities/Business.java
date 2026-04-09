package server.rem.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;
import server.rem.enums.MailProvider;
import server.rem.enums.PhoneDriver;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "businesses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Business extends Base {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id", nullable = false)
    // @JsonBackReference
    @JsonIgnoreProperties({ "businessesOwned", "businesses" })
    private User owner;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "business_user", joinColumns = @JoinColumn(name = "business_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("businesses")
    private Set<User> users;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "slug", length = 100)
    private String slug;

    @Column(name = "logo_url", length = 255, nullable = true)
    private String logoUrl;

    @Column(name = "work_start_time", nullable = true)
    private LocalTime workStartTime;

    @Column(name = "insurance_contribution_salary", nullable = true)
    @Builder.Default
    private Integer insuranceContributionSalary = 0;

    @Column(name = "twilio_account_sid", nullable = true)
    private String twilioAccountSid;

    @Column(name = "twilio_auth_token", nullable = true)
    private String twilioAuthToken;

    @Column(name = "twilio_phone_number", nullable = true)
    private String twilioPhoneNumber;

    @Column(name = "vonage_api_key", nullable = true)
    private String vonageApiKey;

    @Column(name = "vonage_api_secret", nullable = true)
    private String vonageApiSecret;

    @Column(name = "cloudinary_cloud_name", nullable = true)
    private String cloudinaryCloudName;

    @Column(name = "cloudinary_api_key", nullable = true)
    private String cloudinaryApiKey;

    @Column(name = "cloudinary_api_secret", nullable = true)
    private String cloudinaryApiSecret;

    @Column(name = "resend_api_key", nullable = true)
    private String resendApiKey;

    @Column(name = "resend_email", nullable = true)
    private String resendEmail;

    @Column(name = "mail_host", nullable = true)
    private String mailHost;

    @Column(name = "mail_port", nullable = true)
    private Integer mailPort;

    @Column(name = "mail_username", nullable = true)
    private String mailUsername;

    @Column(name = "mail_password", nullable = true)
    private String mailPassword;

    @Column(name = "send_grid_api_key", nullable = true)
    private String sendGridApiKey;

    @Column(name = "send_grid_username", nullable = true)
    private String sendGridUsername;

    @Column(name = "mailgun_api_key", nullable = true)
    private String mailgunApiKey;

    @Column(name = "mailgun_domain", nullable = true)
    private String mailgunDomain;

    @Column(name = "mailgun_username", nullable = true)
    private String mailgunUsername;

    @Enumerated(EnumType.STRING)
    @Column(name = "mail_provider", nullable = true)
    @Builder.Default
    private MailProvider mailProvider = MailProvider.SMTP;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_driver", nullable = true)
    @Builder.Default
    private PhoneDriver phoneDriver = PhoneDriver.TWILIO;
}