package server.rem.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import server.rem.dtos.APIResponse;
import server.rem.dtos.template.CreateTemplateDto;
import server.rem.entities.Template;
import server.rem.services.TemplateService;


@RestController
@RequestMapping("/templates")
@AllArgsConstructor
public class TemplateController {
    private final TemplateService templateService;

    @PostMapping
    public ResponseEntity<APIResponse<Template>> createTemplate(@Valid @RequestBody CreateTemplateDto dto) {
        return ResponseEntity.ok().body(APIResponse.success(
            201,
            "Template created successfully",
            templateService.createTemplate(dto))
        );
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Template>>> getTemplates() {
        return ResponseEntity.ok().body(APIResponse.success(
            200,
            "Templates retrieved successfully",
            templateService.getTemplates())
        );
    }
}
