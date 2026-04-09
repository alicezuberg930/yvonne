package server.rem.dtos.campaign;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryCampaignDto {
    private String limit;

    private String page;

    private String businessId;
}
