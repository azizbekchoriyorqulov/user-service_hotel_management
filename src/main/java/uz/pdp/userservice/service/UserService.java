package uz.pdp.userservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.userservice.domain.dto.UserRequestDto;
import uz.pdp.userservice.domain.dto.response.JwtResponse;
import uz.pdp.userservice.domain.entity.user.RoleEntity;
import uz.pdp.userservice.domain.entity.user.UserEntity;
import uz.pdp.userservice.domain.entity.user.UserState;
import uz.pdp.userservice.exception.AuthenticationFailedException;
import uz.pdp.userservice.exception.DataNotFoundException;
import uz.pdp.userservice.exception.OneTimeCodeVerificationException;
import uz.pdp.userservice.exception.UserBadRequestException;
import uz.pdp.userservice.repository.PermissionRepository;
import uz.pdp.userservice.repository.RoleRepository;
import uz.pdp.userservice.repository.UserRepository;
import uz.pdp.userservice.service.auth.JwtService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final ModelMapper modelMapper;
    private final Random random = new Random(100000);

    @Transactional
    public UserEntity save(UserRequestDto userRequestDto) {
        checkUserEmail(userRequestDto.getEmail());

        UserEntity userEntity = modelMapper.map(userRequestDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userEntity.setVerificationCode(generateVerificationCode());
        userEntity.setState(UserState.UNVERIFIED);
        userRepository.save(userEntity);
        mailService.sendVerificationCode(userEntity);
        return userEntity;
    }

    public JwtResponse login(String email, String password) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("user not found"));

        if(passwordEncoder.matches(password,user.getPassword())) {
            return new JwtResponse(jwtService.generateAccessToken(user));
        }

        throw new AuthenticationFailedException("wrong password or email");
    }

    private String generateVerificationCode() {
        return String.valueOf(random.nextInt(1000000));
    }

    private void checkUserEmail(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new UserBadRequestException("email already exists");
        }
    }


    private List<RoleEntity> getRolesFromStrings(List<String> roles) {
        return roleRepository.findRoleEntitiesByNameIn(roles);
    }

    public String verifyUser(UUID id, String code) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("user not found"));
        checkVerificationCode(code, userEntity);

        userEntity.setState(UserState.ACTIVE);
        userRepository.save(userEntity);
        return "User is verified";
    }

    private void checkVerificationCode(String code, UserEntity userEntity) {
        LocalDateTime updatedDate = userEntity.getUpdatedDate();

        if(updatedDate.plusMinutes(10).isBefore(LocalDateTime.now())) {
            throw new OneTimeCodeVerificationException("Verification code expired");
        }
        if(!Objects.equals(userEntity.getVerificationCode(), code)) {
            throw new OneTimeCodeVerificationException("Verification code didn't match");
        }
    }

    public String regenerateVerificationCode(UUID id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("user not found"));
        userEntity.setVerificationCode(generateVerificationCode());
        mailService.sendVerificationCode(userEntity);
        return "email with verification code is sent";
    }


}
