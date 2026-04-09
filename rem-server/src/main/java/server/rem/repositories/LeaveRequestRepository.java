package server.rem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import server.rem.entities.*;
import server.rem.enums.LeaveStatus;
import server.rem.enums.LeaveType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, String>, JpaSpecificationExecutor<LeaveRequest> {
    List<LeaveRequest> findAllByUser(User user);

    Optional<LeaveRequest> findByUserAndBusinessAndTypeAndStatus(User user, Business business, LeaveType unpaid, LeaveStatus approved);

    List<LeaveRequest> findByUserAndBusinessAndTypeAndStatusAndStartDateGreaterThanEqualAndEndDateLessThanEqual(User user, Business business, LeaveType type, LeaveStatus status, LocalDate startDate, LocalDate endDate);
}