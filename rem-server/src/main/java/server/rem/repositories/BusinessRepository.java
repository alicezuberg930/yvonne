package server.rem.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import server.rem.entities.Business;
import server.rem.entities.User;

import java.util.List;
import java.util.Optional;

public interface BusinessRepository extends JpaRepository<Business, String> {
    @EntityGraph(attributePaths = { "users", "owner" })
    List<Business> findAllByOwner(User user);

    @EntityGraph(attributePaths = { "users", "owner" })
    List<Business> findAll();

    @EntityGraph(attributePaths = { "users", "owner" })
    Optional<Business> findById(String id);
}