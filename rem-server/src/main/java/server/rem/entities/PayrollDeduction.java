package server.rem.entities;

import jakarta.persistence.*;
import lombok.*;
import server.rem.enums.DeductionType;

@Entity
@Table(name = "payroll_deductions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollDeduction extends Base {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payroll_record_id", nullable = false)
    private PayrollItem payrollItem;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DeductionType type;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "note", length = 500)
    private String note;
}