package server.rem.specifications;

import org.springframework.data.jpa.domain.Specification;

import server.rem.dtos.leave_request.QueryLeaveRequestDto;
import server.rem.entities.LeaveRequest;

import java.time.LocalDate;

public class LeaveRequestSpecification {

    public static Specification<LeaveRequest> withFilters(QueryLeaveRequestDto dto) {
        return Specification
                .where(hasBusinessId(dto.getBusinessId()))
                .and(hasStartDate(dto.getStartDate()))
                .and(hasEndDate(dto.getEndDate()));
    }

    private static Specification<LeaveRequest> hasBusinessId(String businessId) {
        return (root, query, cb) -> businessId == null ? null : cb.equal(root.get("business").get("id"), businessId);
    }

    private static Specification<LeaveRequest> hasStartDate(LocalDate startDate) {
        return (root, query, cb) -> startDate == null ? null
                : cb.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    private static Specification<LeaveRequest> hasEndDate(LocalDate endDate) {
        return (root, query, cb) -> endDate == null ? null : cb.lessThanOrEqualTo(root.get("endDate"), endDate);
    }
}