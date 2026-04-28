package server.rem.dtos.security;

import lombok.*;

@Getter
@AllArgsConstructor
public class CreateRoleRequest {
    private final String name;
    private final String description;
}
