package server.rem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import server.rem.dtos.template.CreateTemplateDto;
import server.rem.entities.Business;
import server.rem.entities.Template;
import server.rem.mappers.TemplateMapper;
import server.rem.repositories.BusinessRepository;
import server.rem.repositories.TemplateRepository;

@Service
@AllArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;
    private final BusinessRepository businessRepository;
    private final TemplateMapper templateMapper;

    public Template createTemplate(CreateTemplateDto dto) {
        Business business = businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Template template = templateMapper.toEntity(dto, business);
        return templateRepository.save(template);
    }

    public List<Template> getTemplates() {
        return templateRepository.findAll();
    }
}
