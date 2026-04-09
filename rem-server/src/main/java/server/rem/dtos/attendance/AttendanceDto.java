package server.rem.dtos.attendance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import server.rem.enums.CheckInType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDto {
    @NotNull(message = "Check in time must not be null")
    private LocalDateTime checkInTime;

    private String businessId;

    private LocalDateTime checkOutTime;

    private LocalDate date;

    private CheckInType type;

    private String note;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Latitude cannot be blank")
    private BigDecimal latitude;

    @NotBlank(message = "Longitude cannot be blank")
    private BigDecimal longitude;
}