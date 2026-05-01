package server.rem.dtos.calendar_event;

import lombok.*;
import server.rem.dtos.QueryPaginate;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class QueryCalendarEvent extends QueryPaginate {
    private final String businessId;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final String createdById;
}