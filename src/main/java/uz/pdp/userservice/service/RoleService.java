package uz.pdp.userservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.userservice.domain.dto.RoleDto;
import uz.pdp.userservice.domain.entity.user.RoleEntity;
import uz.pdp.userservice.repository.RoleRepository;
import uz.pdp.userservice.repository.UserRepository;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class RoleService {
    /**
     * CRUD Role
     */


    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public RoleEntity createRole(String name, Integer level){
        RoleEntity roleEntity = RoleEntity.builder()
                .name(name).level(level).build();
        return roleRepository.save(roleEntity);
    }

    public RoleEntity createRole(RoleDto roleDto){
        RoleEntity roleEntity = modelMapper.map(roleDto, RoleEntity.class);
        return roleRepository.save(roleEntity);
    }

    public RoleEntity getRoleById(UUID id){
        return roleRepository.findRoleEntitiesById(id);
    }

    public RoleEntity getRoleByName(String name){
        return roleRepository.findRoleEntitiesByName(name);
    }

    public List<RoleEntity> getRoleByLevel(Integer level){
        return roleRepository.findRoleEntitiesByLevel(level);
    }

    public RoleEntity updateRole(UUID id, RoleDto newRoleDto){
        return roleRepository.updateNameAndLevel(
                newRoleDto.getName(),
                Integer.parseInt(newRoleDto.getLevel()),
                id
        );
    }

//    public RoleEntity updateByUser(UUID roleId, UUID userId, UUID changeMan){
//        UserEntity userEntity = userRepository.findById(userId).orElseThrow(()->new DataNotFoundException("User not found"));
//        UserEntity userEntity1= userRepository.findById(changeMan).orElseThrow(()->new DataNotFoundException("ChangeMan not found"));
//        if(userEntity1.getRoles().get())
//    }

    public void deleteRole(UUID id){
        roleRepository.deleteById(id);
    }


    public List<RoleEntity> getAll(){
        return roleRepository.findAll();
    }




}
