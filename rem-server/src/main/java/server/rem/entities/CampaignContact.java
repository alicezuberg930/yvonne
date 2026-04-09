package server.rem.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "campaign_contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignContact {
    @EmbeddedId
    private CampaignContactId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("campaignId") 
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("contactId")
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;
}