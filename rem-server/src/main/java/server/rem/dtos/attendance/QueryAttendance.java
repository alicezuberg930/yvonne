package server.rem.dtos.attendance;

import lombok.*;
import lombok.experimental.SuperBuilder;
import server.rem.dtos.QueryPaginate;

import java.time.LocalDate;

@Getter
@SuperBuilder
public class QueryAttendance extends QueryPaginate {
    private LocalDate startDate;

    private LocalDate endDate;
}
