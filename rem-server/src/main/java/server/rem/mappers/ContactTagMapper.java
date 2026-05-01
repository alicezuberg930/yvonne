package server.rem.mappers;

import org.mapstruct.*;

import server.rem.dtos.tag.CreateContactTagRequest;
import server.rem.entities.Business;
import server.rem.entities.ContactTag;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContactTagMapper {
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "business", source = "business")
    @Mapping(target = "isActive", expression = "java(dto.getIsActive() != null ? dto.getIsActive() : true)")
    ContactTag toEntity(CreateContactTagRequest dto, Business business);

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "business", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(CreateContactTagRequest dto, @MappingTarget ContactTag tag);
}
