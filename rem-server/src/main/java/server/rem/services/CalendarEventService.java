package server.rem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import server.rem.dtos.calendar_event.CreateCalendarEventDto;
import server.rem.dtos.calendar_event.QueryCalendarEventDto;
import server.rem.entities.Business;
import server.rem.entities.CalendarEvent;
import server.rem.entities.User;
import server.rem.mappers.CalendarEventMapper;
import server.rem.repositories.BusinessRepository;
import server.rem.repositories.CalendarEventRepository;
import server.rem.repositories.UserRepository;
import server.rem.utils.exceptions.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class CalendarEventService {
    private final CalendarEventRepository calendarEventRepository;
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    public CalendarEvent createCalendarEvent(CreateCalendarEventDto dto, String userId) {
        Business business = businessRepository.findById(dto.getBusinessId()).orElseThrow(() -> new ResourceNotFoundException("Business not found"));
        User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Creator not found"));
        CalendarEvent calendarEvent = CalendarEventMapper.toEntity(dto);
        calendarEvent.setBusiness(business);
        calendarEvent.setCreatedBy(user);
        return calendarEvent;
    }

    public List<CalendarEvent> getCalendarEvents(QueryCalendarEventDto dto) {
        Business business = businessRepository.findById(dto.getBusinessId()).orElseThrow(() -> new ResourceNotFoundException("Business not found"));
        List<CalendarEvent> calendarEvents = calendarEventRepository.findByBusinessAndStartDateGreaterThanEqualAndEndDateLessThanEqual(business, dto.getStartDate(), dto.getEndDate());
        return calendarEvents;
    } 
}
