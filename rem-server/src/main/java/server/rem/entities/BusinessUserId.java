package server.rem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessUserId implements Serializable {
    @Column(name = "business_id", length = 24, nullable = false)
    private String businessId;

    @Column(name = "user_id", length = 24, nullable = false)
    private String userId;
}