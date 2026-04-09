package server.rem.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import server.rem.entities.Business;
import server.rem.entities.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, String> {
    List<Holiday> findByBusinessAndDateBetween(Business business, LocalDate startDate, LocalDate endDate);
}