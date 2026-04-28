package server.rem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import server.rem.dtos.customer_group.CreateCustomerGroupRequest;
import server.rem.dtos.customer_group.QueryCustomerGroup;
import server.rem.entities.Business;
import server.rem.entities.CustomerGroup;
import server.rem.mappers.CustomerGroupMapper;
import server.rem.repositories.BusinessRepository;
import server.rem.repositories.CustomerGroupRepository;
import server.rem.utils.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerGroupService {
    private final CustomerGroupRepository customerGroupRepository;
    private final BusinessRepository businessRepository;
    private final CustomerGroupMapper customerGroupMapper;

    public List<CustomerGroup> getCustomerGroupList(QueryCustomerGroup dto) {
        Business business = businessRepository.findById(dto.getBusinessId()).orElseThrow(() -> new ResourceNotFoundException("Business not found"));
        return customerGroupRepository.findByBusiness(business);
    }

    public CustomerGroup getById(String id) {
        return customerGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer group not found"));
    }

    public CustomerGroup create(CreateCustomerGroupRequest dto) {
        Business business = businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));
        // if (customerGroupRepository.existsByNameAndBusinessId(dto.getName(), dto.getBusinessId())) {
        //     throw new RuntimeException("Group with name '" + dto.getName() + "' already exists in this business");
        // }
        CustomerGroup group = customerGroupMapper.toEntity(dto, business);
        return customerGroupRepository.save(group);
    }

    public CustomerGroup update(String id, CreateCustomerGroupRequest dto) {
        CustomerGroup group = getById(id);
        Business business = businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));
        customerGroupMapper.updateEntity(dto, business, group);
        return customerGroupRepository.save(group);
    }

    public void delete(String id) {
        customerGroupRepository.delete(getById(id));
    }
}