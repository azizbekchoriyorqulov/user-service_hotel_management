package uz.pdp.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.userservice.domain.dto.LoginDto;
import uz.pdp.userservice.domain.dto.UserRequestDto;
import uz.pdp.userservice.domain.dto.response.JwtResponse;
import uz.pdp.userservice.domain.entity.user.UserEntity;
import uz.pdp.userservice.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/user/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    @PostMapping("/sign-up")
    public UserEntity signUp(@RequestBody UserRequestDto userRequestDto) {
        return userService.save(userRequestDto);
    }

    @GetMapping("/{id}/verification-code")
    public String regenerateVerificationCode(@PathVariable UUID id) {
        return userService.regenerateVerificationCode(id);
    }

    @PutMapping("/{id}/verify")
    public String verify(
            @PathVariable UUID id,
            @RequestParam String code
    ) {
//        System.out.println(id);
//        System.out.println(code);
//        return id.toString();
        return userService.verifyUser(id, code);
    }

    @GetMapping("/login")
    public JwtResponse login(
            @RequestBody LoginDto loginDto
    ) {
        return userService.login(loginDto.getEmail(), loginDto.getPassword());
    }




}
