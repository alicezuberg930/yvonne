package server.rem.specifications;

import org.springframework.data.jpa.domain.Specification;

import server.rem.dtos.attendance.QueryAttendanceDto;
import server.rem.entities.Attendance;

import java.time.LocalDate;

public class AttendanceSpecification {

    public static Specification<Attendance> withFilters(QueryAttendanceDto dto) {
        return Specification
                .where(hasUserId(dto.getUserId()))
                .and(hasStartDate(dto.getStartDate()))
                .and(hasEndDate(dto.getEndDate()));
    }

    private static Specification<Attendance> hasUserId(String userId) {
        return (root, query, cb) -> userId == null ? null : cb.equal(root.get("user").get("id"), userId);
    }

    private static Specification<Attendance> hasStartDate(LocalDate startDate) {
        return (root, query, cb) -> startDate == null ? null : cb.greaterThanOrEqualTo(root.get("date"), startDate);
    }

    private static Specification<Attendance> hasEndDate(LocalDate endDate) {
        return (root, query, cb) -> endDate == null ? null : cb.lessThanOrEqualTo(root.get("date"), endDate);
    }
}