package server.rem.dtos.template;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.*;
import server.rem.views.Views;

@Getter
@AllArgsConstructor
public class TemplateResponse {
    @JsonView(Views.Public.class)
    private final String id;

    @JsonView(Views.Public.class)
    private final String name;

    @JsonView(Views.Public.class)
    private final String header;

    @JsonView(Views.Public.class)
    private final String body;

    @JsonView(Views.Public.class)
    private final String footer;

    @JsonView(Views.Public.class)
    private final String contactPhone;

    @JsonView(Views.Public.class)
    private final String websiteUrl;
}