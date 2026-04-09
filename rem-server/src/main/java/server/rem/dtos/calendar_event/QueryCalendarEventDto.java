package server.rem.dtos.calendar_event;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QueryCalendarEventDto {
    private String businessId;

    private LocalDate startDate;

    private LocalDate endDate;

    private String createdById;
}
