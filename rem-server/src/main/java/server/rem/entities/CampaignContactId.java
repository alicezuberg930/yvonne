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
public class CampaignContactId implements Serializable {
    @Column(name = "campaign_id", length = 24, nullable = false)
    private String campaignId;

    @Column(name = "contact_id", length = 24, nullable = false)
    private String contactId;
}