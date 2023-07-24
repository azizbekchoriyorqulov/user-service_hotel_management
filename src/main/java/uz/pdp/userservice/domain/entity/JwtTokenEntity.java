package uz.pdp.userservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "jwt-token")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtTokenEntity extends BaseEntity {
    private String token;
    private LocalDateTime expireDate;
}
