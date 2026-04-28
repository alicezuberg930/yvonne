package server.rem.dtos.calendar_event;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import server.rem.enums.CalendarEventType;

@Getter
@AllArgsConstructor
public class CreateCalendarEventRequest {
    @NotBlank(message = "Business id is required")
    private final String businessId;

    @NotBlank(message = "Title is required")
    private final String title;

    private final String description;

    @NotNull(message = "Start date is required")
    private final LocalDate startDate;

    @NotNull(message = "End date is required")
    private final LocalDate endDate;

    @NotNull(message = "End time is required")
    private final LocalTime startTime;

    @NotNull(message = "End time is required")
    private final LocalTime endTime;

    @NotNull(message = "Calendar type is required")
    private final CalendarEventType type;

}
