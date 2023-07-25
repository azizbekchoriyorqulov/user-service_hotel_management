package uz.pdp.userservice.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
}
