package server.rem.dtos.sms;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SendSmsDto {
    @NotBlank(message = "To number is required")
    private String toNumber;

    @NotBlank(message = "Text message is required")
    private String textMessage;
}
