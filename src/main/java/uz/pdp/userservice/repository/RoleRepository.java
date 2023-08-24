package uz.pdp.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.userservice.domain.entity.user.RoleEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    RoleEntity findRoleEntitiesById(UUID id);

    List<RoleEntity> findRoleEntitiesByLevel(Integer level);

    RoleEntity findRoleEntitiesByName(String name);

    @Modifying
    @Query("UPDATE role p set p.name = :name, p.level = :level where p.id = :id")
    RoleEntity updateNameAndLevel(@Param("name")String name, @Param("level")int level, @Param("id")UUID id);

    List<RoleEntity> findRoleEntitiesByNameIn(List<String> roles);
}
