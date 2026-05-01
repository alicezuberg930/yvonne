package server.rem.dtos.contact;

import lombok.*;
import server.rem.dtos.QueryPaginate;
import server.rem.enums.ContactType;

@Getter
@AllArgsConstructor
public class QueryContact extends QueryPaginate {
    private String customerGroupId;

    private ContactType type;
}
