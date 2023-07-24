package uz.pdp.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.userservice.domain.entity.JwtTokenEntity;

import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<JwtTokenEntity, UUID> {
}
