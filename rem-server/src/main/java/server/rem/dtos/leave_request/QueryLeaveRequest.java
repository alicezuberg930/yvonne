package server.rem.dtos.leave_request;

import lombok.*;
import lombok.experimental.SuperBuilder;
import server.rem.dtos.QueryPaginate;

import java.time.LocalDate;

@Getter
@SuperBuilder
public class QueryLeaveRequest extends QueryPaginate {
    private final String businessId;

    private final LocalDate startDate;

    private final LocalDate endDate;

}
