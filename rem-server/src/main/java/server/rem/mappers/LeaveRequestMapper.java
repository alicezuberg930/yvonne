package server.rem.mappers;

import server.rem.dtos.leave_request.CreateLeaveRequest;
import server.rem.entities.LeaveRequest;

public class LeaveRequestMapper {
    public static LeaveRequest toEntity(CreateLeaveRequest dto) {
        return LeaveRequest.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .days(dto.getDays())
                .type(dto.getType())
                .reason(dto.getReason())
                .build();
    }
}
