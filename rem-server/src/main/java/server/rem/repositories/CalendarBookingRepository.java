package server.rem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.rem.entities.CalendarBooking;

public interface CalendarBookingRepository extends JpaRepository<CalendarBooking, String> {

}
