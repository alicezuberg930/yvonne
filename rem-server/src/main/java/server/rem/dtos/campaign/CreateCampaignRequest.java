package server.rem.dtos.campaign;

import java.time.Instant;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import server.rem.enums.CampaignSendType;

@Getter
@SuperBuilder
public class CreateCampaignRequest {
    @NotBlank(message = "Template id is required")
    private final String templateId;

    @NotNull(message = "Send type is required")
    private final CampaignSendType sendType;

    @NotBlank(message = "Name is required")
    private final String name;

    private final String description;

    private final Instant scheduleAt;

    private final List<String> contactIds;
}
