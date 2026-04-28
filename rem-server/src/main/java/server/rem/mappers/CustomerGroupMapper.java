package server.rem.mappers;

import org.mapstruct.*;

import server.rem.dtos.customer_group.CreateCustomerGroupRequest;
import server.rem.entities.Business;
import server.rem.entities.CustomerGroup;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerGroupMapper {

    // @Mapping(target = "id", ignore = true)
    // @Mapping(target = "createdAt", ignore = true)
    // @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "business", source = "business")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "percentage", source = "dto.percentage")
    CustomerGroup toEntity(CreateCustomerGroupRequest dto, Business business);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "business", source = "business")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "percentage", source = "dto.percentage")
    void updateEntity(CreateCustomerGroupRequest dto, Business business, @MappingTarget CustomerGroup group);
}
