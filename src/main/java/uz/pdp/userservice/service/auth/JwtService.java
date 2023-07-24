package uz.pdp.userservice.service.auth;


import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import uz.pdp.userservice.domain.dto.response.JwtResponse;
import uz.pdp.userservice.domain.entity.user.UserEntity;
import uz.pdp.userservice.repository.TokenRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.access.expiry}")
    private long accessTokenExpiry;
    @Value("${jwt.refresh.expiry}")
    private long refreshToken;

    private final TokenRepository tokenRepository;

    public String generateAccessToken(UserEntity userEntity){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .setSubject(userEntity.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+accessTokenExpiry))
                .addClaims(Map.of("roles",getRoles(userEntity.getAuthorities())))
                .compact();
    }
    public String generateRefreshToken(UserEntity userEntity) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setSubject(userEntity.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + refreshToken))
                .compact();
    }

    public List<String> getRoles(Collection<?extends GrantedAuthority> roles){
        return roles.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    public Jws<Claims> extractToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    }

//    public JwtResponse validateToken(String token) {
//        return
//
//    }
}
