package server.rem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import server.rem.entities.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
