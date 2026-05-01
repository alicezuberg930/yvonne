package server.rem.dtos.campaign;

import lombok.*;
import server.rem.dtos.QueryPaginate;

@Getter
@AllArgsConstructor
public class QueryCampaign extends QueryPaginate {
    private final String contactId;
}