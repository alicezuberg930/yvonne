package server.rem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import server.rem.dtos.security.CreateRoleRequest;
import server.rem.entities.Role;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toEntity(CreateRoleRequest dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(CreateRoleRequest dto, @MappingTarget Role role);
}