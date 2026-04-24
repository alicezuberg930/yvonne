package server.rem.dtos.template;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTemplateDto {
    @NotBlank(message = "Template name is required")
    private String name;

    @NotBlank(message = "Template header is required")
    private String header;

    @NotBlank(message = "Template body is required")
    private String body;

    private String footer;

    private String contactPhone;

    private String websiteUrl;
}