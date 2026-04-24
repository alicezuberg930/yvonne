package server.rem.dtos.template;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.*;
import server.rem.views.Views;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateResponse {
    @JsonView(Views.Public.class)
    private String id;

    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private String header;

    @JsonView(Views.Public.class)
    private String body;

    @JsonView(Views.Public.class)
    private String footer;

    @JsonView(Views.Public.class)
    private String contactPhone;

    @JsonView(Views.Public.class)
    private String websiteUrl;
}
