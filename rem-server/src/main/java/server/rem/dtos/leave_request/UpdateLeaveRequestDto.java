package server.rem.dtos.leave_request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import server.rem.enums.LeaveStatus;
import server.rem.enums.LeaveType;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLeaveRequestDto {
    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    private Double days;

    @NotNull(message = "Leave type is required")
    private LeaveType type;

    @NotBlank(message = "Reason is required")
    private String reason;

    private LeaveStatus status;

    private String approverNote;
}
