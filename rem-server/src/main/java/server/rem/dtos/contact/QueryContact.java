package server.rem.dtos.contact;

import lombok.*;
import server.rem.dtos.PaginateQuery;
import server.rem.enums.ContactType;

@Getter
@AllArgsConstructor
public class QueryContact extends PaginateQuery{
    private final String customerGroupId;

    private final ContactType type;
}
