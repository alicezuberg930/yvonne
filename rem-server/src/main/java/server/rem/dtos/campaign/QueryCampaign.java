package server.rem.dtos.campaign;

import lombok.*;
import server.rem.dtos.PaginateQuery;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QueryCampaign extends PaginateQuery {
    private String contactId;
}
