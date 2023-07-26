package uz.pdp.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.userservice.domain.entity.user.PermissionEntity;
import uz.pdp.userservice.exception.DataNotFoundException;
import uz.pdp.userservice.repository.PermissionRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionEntity createPermission(String name){
        return permissionRepository.save(new PermissionEntity().builder()
                .permission(name).build());
    }

    public PermissionEntity getPermissionById(UUID id){
        try {
            return permissionRepository.findPermissionEntitiesById(id);
        }catch (DataNotFoundException e){
            throw new DataNotFoundException("Permission not found");
        }
    }

    public PermissionEntity updatePermission(UUID id, String name){
        PermissionEntity permission = getPermissionById(id);
        permission.setPermission(name);
        return permissionRepository.save(permission);
    }

    public void deleteById(UUID id){
        permissionRepository.deleteById(id);
    }
}
