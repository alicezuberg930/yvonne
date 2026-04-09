package server.rem.dtos.attendance;

import lombok.*;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
@ToString
@Setter
public class QueryAttendanceDto {
    private String userId;

    private LocalDate startDate;

    private LocalDate endDate;

    private String limit;

    private String page;
}
