package server.rem.dtos.calendar_event;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class QueryCalendarEvent {
    private String businessId;

    private LocalDate startDate;

    private LocalDate endDate;

    private String createdById;
}
