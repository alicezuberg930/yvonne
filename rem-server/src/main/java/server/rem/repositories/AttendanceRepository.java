package server.rem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import server.rem.entities.Attendance;
import server.rem.entities.Business;
import server.rem.entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, String>, JpaSpecificationExecutor<Attendance> {
    Optional<Attendance> findByBusinessAndUserAndDate(Business business, User user, LocalDate date);

    boolean existsByBusinessAndUserAndDate(Business business, User user, LocalDate date);

    List<Attendance> findByUserAndBusinessAndDateBetween(User user, Business business, LocalDate startDate, LocalDate endDate);
}