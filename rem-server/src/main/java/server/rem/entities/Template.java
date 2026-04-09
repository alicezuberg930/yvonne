package server.rem.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Template extends Base {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "header", nullable = false, columnDefinition = "LONGTEXT")
    private String header;

    @Column(name = "body", nullable = false, columnDefinition = "LONGTEXT")
    private String body;

    @Column(name = "footer", nullable = true, columnDefinition = "LONGTEXT")
    private String footer;

    @Column(name = "contact_phone", nullable = true)
    private String contactPhone;

    @Column(name = "website_url", nullable = true)
    private String websiteUrl;
}