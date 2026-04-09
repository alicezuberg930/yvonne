package server.rem.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.type.PhoneNumber;
import com.vonage.client.VonageClient;
import com.vonage.client.messages.MessageResponse;
import com.vonage.client.messages.sms.SmsTextRequest;
import com.twilio.rest.api.v2010.account.Message;
import server.rem.dtos.sms.SendSmsDto;

@Service
public class SmsService {

    @Value("${twilio.phone-number}")
    private String fromTwilioNumber;

    @Value("${vonage.api-key}")
    private String vonageApiKey;

    @Value("${vonage.api-secret}")
    private String vonageApiSecret;

    public void sendSmsTwilio(SendSmsDto dto) {
        try {
            Message msg = Message.creator(
                    new PhoneNumber(dto.getToNumber()),
                    new PhoneNumber(fromTwilioNumber), dto.getTextMessage()).create();
            System.out.println(msg.getBody());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

     private VonageClient getClient() {
         return VonageClient.builder()
                 .apiKey(vonageApiKey)
                 .apiSecret(vonageApiSecret)
                 .build();
     }

     public void sendSmsVonage(SendSmsDto dto) {
//         VerifyResponse response = getClient().getVerifyClient().verify("84395417455", "Vonage");
//
//         if (response.getStatus() == VerifyStatus.OK) {
//             System.out.printf("RequestID: %s", response.getRequestId());
//         } else {
//             System.out.printf("ERROR! %s: %s", response.getStatus(), response.getErrorText());
//         }

//         CheckResponse response = getClient().getVerifyClient().check("3792179b0be14af4b6a7e1fa7615e37f", "6750");
//
//         if (response.getStatus() == VerifyStatus.OK) {
//             System.out.println("Verification Successful");
//         } else {
//             System.out.println("Verification failed: " + response.getErrorText());
//         }
         try {
             MessageResponse response = getClient().getMessagesClient().sendMessage(
                     SmsTextRequest.builder()
                             .from("Vonage")
                             .to(dto.getToNumber())
                             .text(dto.getTextMessage())
                             .build());
             System.out.println(response.getMessageUuid());
         } catch (Exception e) {
             throw new RuntimeException(e.getMessage());
         }
     }
}