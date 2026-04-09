package server.rem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.rem.entities.ContactTag;
 
import java.util.List;
 
@Repository
public interface ContactTagRepository extends JpaRepository<ContactTag, String> {
    List<ContactTag> findByIsActive(Boolean isActive);
    boolean existsByName(String name);
}