package server.rem.mappers;

import server.rem.dtos.payroll.CreatePayrollPeriodDto;
import server.rem.entities.PayrollPeriod;

public class PayrollPeriodMapper {
    public static PayrollPeriod toEntity(CreatePayrollPeriodDto dto) {
        return PayrollPeriod.builder()
                .name(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
