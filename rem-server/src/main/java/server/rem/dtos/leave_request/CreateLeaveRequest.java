package server.rem.dtos.leave_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import server.rem.enums.LeaveType;

import java.time.LocalDate;

@Getter
@SuperBuilder
public class CreateLeaveRequest {
    @NotBlank(message = "Business id is required")
    private final String businessId;

    @NotNull(message = "Start date is required")
    private final LocalDate startDate;

    @NotNull(message = "End date is required")
    private final LocalDate endDate;

    private final Double days;

    @NotNull(message = "Leave type is required")
    private final LeaveType type;

    @NotBlank(message = "Reason is required")
    private final String reason;
}
