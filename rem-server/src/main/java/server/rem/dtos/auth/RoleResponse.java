package server.rem.dtos.auth;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.*;
import server.rem.views.Views;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse {
    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private String description;

    @JsonView(Views.Public.class)
    private List<PermissionResponse> permissions;
}
