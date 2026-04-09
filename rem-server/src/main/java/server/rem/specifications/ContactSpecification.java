package server.rem.specifications;

import org.springframework.data.jpa.domain.Specification;
import server.rem.dtos.contact.QueryContactDto;
import server.rem.entities.Contact;
import server.rem.enums.ContactType;

public class ContactSpecification {

    public static Specification<Contact> withFilters(QueryContactDto dto) {
        return Specification
                .where(hasBusinessId(dto.getBusinessId()))
                .and(hasType(dto.getType()))
                .and(hasCustomerGroup(dto.getCustomerGroupId()));
    }

    private static Specification<Contact> hasBusinessId(String businessId) {
        return (root, query, cb) -> businessId == null ? null : cb.equal(root.get("business").get("id"), businessId);
    }

    private static Specification<Contact> hasType(ContactType type) {
        return (root, query, cb) -> type == null ? null : cb.equal(root.get("type"), type);
    }

    private static Specification<Contact> hasCustomerGroup(String customerGroupId) {
        return (root, query, cb) -> customerGroupId == null ? null
                : cb.equal(root.get("customerGroup").get("id"), customerGroupId);
    }

}