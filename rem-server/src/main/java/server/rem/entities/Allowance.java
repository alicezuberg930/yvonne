package server.rem.entities;

import jakarta.persistence.*;
import lombok.*;
import server.rem.enums.AllowanceType;

@Entity
@Table(name = "allowances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Allowance extends Base {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "is_active")
    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AllowanceType type;
}
