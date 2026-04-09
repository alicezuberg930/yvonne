package server.rem.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerGroup extends Base {
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @Column(name = "percentage", nullable = true)
    private Double percentage;
}
