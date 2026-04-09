package server.rem.dtos.calendar_event;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import server.rem.enums.CalendarEventType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCalendarEventDto {
    @NotBlank(message = "Business id is required")
    private String businessId;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "End time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "Calendar type is required")
    private CalendarEventType type;

}
