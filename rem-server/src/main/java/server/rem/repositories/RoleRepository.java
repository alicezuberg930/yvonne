package server.rem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import server.rem.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);
}
