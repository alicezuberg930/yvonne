package server.rem.dtos.security;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRoleDto {
    private String name;
    private String description;
}
