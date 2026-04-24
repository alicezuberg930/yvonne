package server.rem.dtos.campaign;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.*;
import server.rem.dtos.contact.ContactResponse;
import server.rem.dtos.template.TemplateResponse;
import server.rem.views.Views;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignResponse {
    @JsonView(Views.Public.class)
    private String id;

    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private String description;

    @JsonView(Views.Public.class)
    private String sendType;

    @JsonView(Views.Public.class)
    private String scheduleAt;

    @JsonView(Views.Public.class)
    private String status;

    @JsonView(Views.Public.class)
    private TemplateResponse template;

    @JsonView(Views.Public.class)
    private List<ContactResponse> contacts;
}