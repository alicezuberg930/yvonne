package server.rem.dtos.payroll;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
public class CreatePayrollPeriodRequest {
    @NotBlank(message = "Business id is required")
    private final String businessId;

    @NotBlank(message = "Name is required")
    private final String name;

    @NotNull(message = "Start date is required")
    private final LocalDate startDate;

    @NotNull(message = "End date is required")
    private final LocalDate endDate;
}