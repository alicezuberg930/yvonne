package server.rem.entities;

import jakarta.persistence.*;
import lombok.*;
import server.rem.enums.BonusType;

@Entity
@Table(name = "payroll_bonuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollBonus extends Base {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payroll_record_id", nullable = false)
    private PayrollItem payrollItem;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private BonusType type;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "note", length = 500)
    private String note;
}