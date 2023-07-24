package uz.pdp.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.userservice.domain.dto.response.ApiResponse;
import uz.pdp.userservice.domain.dto.response.JwtResponse;
import uz.pdp.userservice.service.auth.JwtService;

@RestController
@RequestMapping("/user/api/v1/auth")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;


    @GetMapping("/validateToken")
    public ApiResponse validateToken() {
        return new ApiResponse("jwt validated", 200, null);
    }
}
