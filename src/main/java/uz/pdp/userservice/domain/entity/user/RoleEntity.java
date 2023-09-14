package uz.pdp.userservice.domain.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.userservice.domain.entity.BaseEntity;

import java.util.List;


public enum RoleEntity  {
        USER, ADMIN, SUPER_ADMIN, MANAGER;
}
