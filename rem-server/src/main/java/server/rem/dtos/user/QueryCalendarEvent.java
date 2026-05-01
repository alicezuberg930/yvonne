package server.rem.dtos.user;

import lombok.*;
import server.rem.dtos.QueryPaginate;

@Getter
@AllArgsConstructor
public class QueryCalendarEvent extends QueryPaginate {
    private final String role;
}