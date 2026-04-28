package server.rem.dtos.security;

import lombok.*;

@Getter
@AllArgsConstructor
public class CreatePermissionRequest {
    private final String name;
    private final String description;
}
