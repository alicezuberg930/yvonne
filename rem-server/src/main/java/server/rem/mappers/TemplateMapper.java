package server.rem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import server.rem.dtos.template.CreateTemplateDto;
import server.rem.entities.Business;
import server.rem.entities.Template;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TemplateMapper {
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "header", source = "dto.header")
    @Mapping(target = "body", source = "dto.body")
    @Mapping(target = "footer", source = "dto.footer")
    @Mapping(target = "contactPhone", source = "dto.contactPhone")
    @Mapping(target = "websiteUrl", source = "dto.websiteUrl")
    @Mapping(target = "business", source = "business")
    Template toEntity(CreateTemplateDto dto, Business business);

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "header", source = "dto.header")
    @Mapping(target = "body", source = "dto.body")
    @Mapping(target = "footer", source = "dto.footer")
    @Mapping(target = "contactPhone", source = "dto.contactPhone")
    @Mapping(target = "websiteUrl", source = "dto.websiteUrl")
    @Mapping(target = "business", source = "business")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(CreateTemplateDto dto, Business business, @MappingTarget Template template);

}
