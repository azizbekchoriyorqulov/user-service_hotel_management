package uz.pdp.userservice.domain.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import uz.pdp.userservice.domain.entity.BaseEntity;

import java.util.List;

@Entity(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleEntity extends BaseEntity {
    private String name;
    private int level;
    @ManyToMany
    private List<PermissionEntity> permissions;
}
