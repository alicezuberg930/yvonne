package server.rem.dtos.leave_request;

import lombok.*;
import server.rem.dtos.QueryPaginate;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class QueryLeaveRequest extends QueryPaginate {
    private final String businessId;

    private final LocalDate startDate;

    private final LocalDate endDate;

}
