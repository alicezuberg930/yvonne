package server.rem.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import server.rem.entities.Business;
import server.rem.entities.CustomerGroup;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, String> {
    @EntityGraph(attributePaths = { "business" })
    List<CustomerGroup> findByBusiness(Business business);

    @EntityGraph(attributePaths = { "business" })
    Optional<CustomerGroup> findById(String id);

    boolean existsByNameAndBusinessId(String name, String businessId);
}
