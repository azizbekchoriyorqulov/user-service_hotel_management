package uz.pdp.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OneTimeCodeVerificationException extends RuntimeException {
    public OneTimeCodeVerificationException(String message) {
        super(message);
    }
}
