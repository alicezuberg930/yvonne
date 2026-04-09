package server.rem.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import server.rem.entities.Contact;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String>, JpaSpecificationExecutor<Contact> {
    @EntityGraph(attributePaths = { "business", "tag", "customerGroup" })
    Page<Contact> findAll(Specification<Contact> spec, Pageable pageable);

    @EntityGraph(attributePaths = { "business", "tag", "customerGroup" })
    Optional<Contact> findById(String id);

    boolean existsByEmailAndBusinessId(String email, String businessId);
}
