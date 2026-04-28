package server.rem.dtos.contact;

import lombok.*;
import lombok.experimental.SuperBuilder;
import server.rem.dtos.QueryPaginate;
import server.rem.enums.ContactType;

@Getter
@SuperBuilder
public class QueryContact extends QueryPaginate {
    private final String customerGroupId;

    private final ContactType type;
}
