package server.rem.dtos.campaign;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.rem.enums.CampaignSendType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCampaignDto {
    @NotBlank(message = "Template id is required")
    private String templateId;

    @NotNull(message = "Send type is required")
    private CampaignSendType sendType;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    private LocalDateTime scheduleAt;

    private List<String> contactIds;
}
