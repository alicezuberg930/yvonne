package server.rem.dtos.tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import server.rem.enums.Color;
 
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateContactTagDto {
    @NotBlank(message = "Business ID is required")
    private String businessId;

    @NotBlank(message = "Tag name is required")
    private String name;
 
    @NotNull(message = "Color is required")
    private Color color;
 
    private Boolean isActive;
}