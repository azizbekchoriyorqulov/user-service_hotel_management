package uz.pdp.userservice;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import uz.pdp.userservice.domain.dto.MailDto;

import java.net.URI;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        MailDto mailDto = new MailDto("hello", "email");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MailDto> entity = new HttpEntity<>(mailDto, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(
                URI.create("http://localhost:8083/notification/send-single"),
                HttpMethod.POST,
                entity,
                String.class);
        System.out.println(response.getBody());
    }
}
