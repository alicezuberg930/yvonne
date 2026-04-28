package server.rem.mappers;

import server.rem.dtos.payroll.CreatePayrollPeriodRequest;
import server.rem.entities.PayrollPeriod;

public class PayrollPeriodMapper {
    public static PayrollPeriod toEntity(CreatePayrollPeriodRequest dto) {
        return PayrollPeriod.builder()
                .name(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
