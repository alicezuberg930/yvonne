package server.rem.dtos.leave_request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import server.rem.enums.LeaveStatus;

@Getter
@SuperBuilder
public class UpdateLeaveRequest extends CreateLeaveRequest {
    @NotBlank(message = "Status cannot be blank")
    private final LeaveStatus status;

    private final String approverNote;
}
