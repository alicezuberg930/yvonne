package server.rem.dtos.auth;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.*;
import server.rem.views.Views;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionResponse {
    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private String description;
}
