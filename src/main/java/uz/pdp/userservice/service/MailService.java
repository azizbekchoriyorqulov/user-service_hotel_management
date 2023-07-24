package uz.pdp.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.pdp.userservice.domain.dto.MailDto;
import uz.pdp.userservice.domain.entity.user.UserEntity;

import java.net.URI;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {

    private final RestTemplate restTemplate;

    @Value("${services.notification-url}")
    private String notificationServiceUrl;

    @Value("${host}")
    private String host;

    @Value("${server.port}")
    private String port;
    public String sendVerificationCode(UserEntity userEntity) {
        String verificationUrl = generateVerificationUrl(userEntity.getId());
        StringBuilder message = new StringBuilder("This is your verification code: ")
                .append(userEntity.getVerificationCode())
                .append("\n\nLink to enter verification code: ")
                .append(verificationUrl);

        sendMail(message.toString(), userEntity.getEmail(), "/send-single");
        return "verification code is sent";
    }

    private String generateVerificationUrl(UUID id) {
        return String.join("/", host + ":" + port, "user/auth", id.toString(), "verify");
    }

    private void sendMail(String message, String email, String uri) {
        MailDto mailDto = new MailDto(message, email);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MailDto> entity = new HttpEntity<>(mailDto, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(
                URI.create(notificationServiceUrl + uri),
                HttpMethod.POST,
                entity,
                String.class);
    }

}
