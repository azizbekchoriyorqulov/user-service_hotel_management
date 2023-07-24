package uz.pdp.userservice.domain.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.userservice.domain.entity.user.PermissionEntity;
import uz.pdp.userservice.domain.entity.user.RoleEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private String name;

    @NotBlank(message = "email must not be blank")
    private String email;

    @NotBlank(message = "email must not be blank")
    private String password;

//    private List<String> roles;
//
//    private List<String> permissions;
}
