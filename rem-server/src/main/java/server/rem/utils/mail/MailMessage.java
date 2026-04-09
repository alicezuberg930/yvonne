package server.rem.utils.mail;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailMessage {
    private String to;
    private String subject;
    private String htmlBody;
    private String attachmentPath;
}