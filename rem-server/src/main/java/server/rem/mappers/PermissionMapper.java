package server.rem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import server.rem.dtos.security.CreatePermissionRequest;
import server.rem.entities.Permission;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PermissionMapper {
    @Mapping(target = "roles", ignore = true)
    Permission toEntity(CreatePermissionRequest dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(CreatePermissionRequest dto, @MappingTarget Permission permission);
}