package server.rem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import server.rem.dtos.business.CreateBusinessDto;
import server.rem.dtos.business.UpdateBusinessDto;
import server.rem.entities.Business;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BusinessMapper {
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "users", ignore = true)
    Business toEntity(CreateBusinessDto dto);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntity(UpdateBusinessDto dto, @MappingTarget Business entity);
}