package server.rem.dtos.attendance;

import lombok.*;
import server.rem.dtos.PaginateQuery;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class QueryAttendance extends PaginateQuery {
    private LocalDate startDate;

    private LocalDate endDate;
}
