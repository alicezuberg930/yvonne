package server.rem.dtos.tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import server.rem.enums.Color;
 
@Getter
@AllArgsConstructor
public class CreateContactTagRequest {
    @NotBlank(message = "Business ID is required")
    private final String businessId;

    @NotBlank(message = "Tag name is required")
    private final String name;
 
    @NotNull(message = "Color is required")
    private final Color color;
 
    private final Boolean isActive;
}