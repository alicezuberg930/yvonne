package server.rem.dtos.attendance;

import lombok.*;
import server.rem.dtos.QueryPaginate;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class QueryAttendance extends QueryPaginate {
    private LocalDate startDate;

    private LocalDate endDate;
}
