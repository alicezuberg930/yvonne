package server.rem.mappers;

import server.rem.dtos.calendar_event.CreateCalendarEventRequest;
import server.rem.entities.CalendarEvent;

public class CalendarEventMapper {
    public static CalendarEvent toEntity(CreateCalendarEventRequest dto) {
        return CalendarEvent.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .type(dto.getType())
                .build();
    }
}
