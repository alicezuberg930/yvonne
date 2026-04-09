package server.rem.dtos.leave_request;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryLeaveRequestDto {
    private String businessId;

    private LocalDate startDate;

    private LocalDate endDate;

    private String limit;

    private String page;
}
