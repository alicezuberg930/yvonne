package server.rem.dtos.security;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePermissionDto {
    private String name;
    private String description;
}
