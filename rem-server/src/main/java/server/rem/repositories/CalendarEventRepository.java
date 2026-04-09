package server.rem.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import server.rem.entities.Business;
import server.rem.entities.CalendarEvent;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, String>, JpaSpecificationExecutor<CalendarEvent> {
    List<CalendarEvent> findByBusinessAndStartDateGreaterThanEqualAndEndDateLessThanEqual(Business business, LocalDate starDate, LocalDate endDate);
}