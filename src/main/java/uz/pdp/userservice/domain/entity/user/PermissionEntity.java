package uz.pdp.userservice.domain.entity.user;

import ch.qos.logback.core.joran.action.BaseModelAction;
import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.userservice.domain.entity.BaseEntity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionEntity extends BaseEntity {
    private String permission;
}
