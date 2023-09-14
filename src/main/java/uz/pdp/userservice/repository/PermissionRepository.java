package uz.pdp.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> {
    List<PermissionEntity> findPermissionEntitiesByPermissionIn(List<String> permissions);
}
