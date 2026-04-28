package server.rem.dtos.campaign;

import lombok.*;
import lombok.experimental.SuperBuilder;
import server.rem.dtos.QueryPaginate;

@Getter
@SuperBuilder
public class QueryCampaign extends QueryPaginate {
    private final String contactId;
}