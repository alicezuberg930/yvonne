package server.rem.dtos.business;

import jakarta.validation.constraints.Size;
import lombok.*;
import server.rem.enums.MailProvider;
import server.rem.enums.PhoneDriver;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@Builder
public class UpdateBusinessDto {
    @Size(max = 100)
    private String name;

    @Size(max = 100)
    private String slug;

    @Size(max = 255)
    private String logoUrl;

    private LocalTime workStartTime;

    private Integer insuranceContributionSalary;

    @Size(max = 255)
    private String twilioAccountSid;

    @Size(max = 255)
    private String twilioAuthToken;

    @Size(max = 255)
    private String twilioPhoneNumber;

    @Size(max = 255)
    private String vonageApiKey;

    @Size(max = 255)
    private String vonageApiSecret;

    @Size(max = 255)
    private String cloudinaryCloudName;

    @Size(max = 255)
    private String cloudinaryApiKey;

    @Size(max = 255)
    private String cloudinaryApiSecret;

    @Size(max = 255)
    private String resendApiKey;

    @Size(max = 255)
    private String resendEmail;

    @Size(max = 255)
    private String mailHost;

    private Integer mailPort;

    @Size(max = 255)
    private String mailUsername;

    @Size(max = 255)
    private String mailPassword;

    private MailProvider mailProvider;

    private PhoneDriver phoneDriver;

    @Size(max = 255)
    private String sendGridApiKey;

    @Size(max = 255)
    private String sendGridUsername;

    @Size(max = 255)
    private String mailgunApiKey;

    @Size(max = 255)
    private String mailgunDomain;
    
    @Size(max = 255)
    private String mailgunUsername;
}