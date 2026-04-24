package server.rem.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import server.rem.dtos.business.BusinessResponse;
import server.rem.dtos.business.CreateBusinessDto;
import server.rem.dtos.business.UpdateBusinessDto;
import server.rem.entities.Business;
import server.rem.entities.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BusinessMapper {
    @Mapping(target = "owner", source = "owner")
    Business toEntity(CreateBusinessDto dto, User owner);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntity(UpdateBusinessDto dto, @MappingTarget Business entity);

    List<BusinessResponse> toBusinessesResponse(List<Business> businesses);
}