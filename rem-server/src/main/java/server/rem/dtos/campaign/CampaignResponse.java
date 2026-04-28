package server.rem.dtos.campaign;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.*;
import server.rem.dtos.contact.ContactResponse;
import server.rem.dtos.template.TemplateResponse;
import server.rem.views.Views;

@Getter
@AllArgsConstructor
public class CampaignResponse {
    @JsonView(Views.Public.class)
    private final String id;

    @JsonView(Views.Public.class)
    private final String name;

    @JsonView(Views.Public.class)
    private final String description;

    @JsonView(Views.Public.class)
    private final String sendType;

    @JsonView(Views.Public.class)
    private final String scheduleAt;

    @JsonView(Views.Public.class)
    private final String status;

    @JsonView(Views.Public.class)
    private final TemplateResponse template;

    @JsonView(Views.Public.class)
    private final List<ContactResponse> contacts;
}