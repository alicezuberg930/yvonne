package server.rem.dtos.calendar_booking;

import java.time.Instant;

import jakarta.validation.constraints.*;
import lombok.*;
import server.rem.enums.CalendarBookingStatus;

@Getter
@AllArgsConstructor
public class CreateBookingRequest {
    @NotBlank(message = "Contact Id is required")
    @NotEmpty(message = "Contact Id cannot be empty")
    @NotNull(message = "Contact Id cannot be null")
    private final String contactId;

    @NotNull(message = "Start date cannot be null")
    private final Instant bookingStartDate;

    @NotNull(message = "End date cannot be null")
    private final Instant bookingEndDate;

    private final String serviceStaffId;

    private final String correspondentId;

    private final CalendarBookingStatus status;
}