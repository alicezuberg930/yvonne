package server.rem.services;

import lombok.RequiredArgsConstructor;
import server.rem.utils.mail.DynamicMail;
import server.rem.utils.mail.MailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final DynamicMail dynamicMail;

    public void sendMail(String businessId, String to, String subject, String html) throws Exception {
        sendMail(businessId, to, subject, html, null);
    }

    public void sendMail(String businessId, String to, String subject, String html, String attachmentPath) throws Exception {
        dynamicMail.send(businessId, MailMessage.builder()
                .to(to)
                .subject(subject)
                .htmlBody(html)
                .attachmentPath(attachmentPath)
                .build());
    }
}