package server.rem.dtos.auth;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.*;
import server.rem.views.Views;

@Getter
@AllArgsConstructor
public class PermissionResponse {
    @JsonView(Views.Public.class)
    private final String name;

    @JsonView(Views.Public.class)
    private final String description;
}
