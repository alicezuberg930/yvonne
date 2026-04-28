package server.rem.dtos.attendance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import server.rem.enums.CheckInType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreateAttendanceRequest {
    @NotNull(message = "Check in time must not be null")
    private final LocalDateTime checkInTime;

    private final String businessId;

    private final LocalDateTime checkOutTime;

    private final LocalDate date;

    private final CheckInType type;

    private final String note;

    @NotBlank(message = "Address cannot be blank")
    private final String address;

    @NotBlank(message = "Latitude cannot be blank")
    private final BigDecimal latitude;

    @NotBlank(message = "Longitude cannot be blank")
    private final BigDecimal longitude;
}