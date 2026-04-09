package server.rem.entities;

import jakarta.persistence.*;
import lombok.*;
import server.rem.enums.Color;

@Entity
@Table(name = "contact_tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactTag extends Base {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private Color color;

    @Column(name = "is_active", nullable = true)
    @Builder.Default
    private Boolean isActive = true;
}
