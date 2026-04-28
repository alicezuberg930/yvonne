package server.rem.dtos.sms;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@AllArgsConstructor
public class SendSmsRequest {
    @NotBlank(message = "To number is required")
    private final String toNumber;

    @NotBlank(message = "Text message is required")
    private final String textMessage;
}
