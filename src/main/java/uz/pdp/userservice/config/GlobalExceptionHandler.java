package uz.pdp.userservice.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.pdp.userservice.exception.OneTimeCodeVerificationException;
import uz.pdp.userservice.exception.UserBadRequestException;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserBadRequestException.class)
    public ResponseEntity<String> userBadRequestExceptionHandler(UserBadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(OneTimeCodeVerificationException.class)
    public ResponseEntity<String> oneTimeCodeVerificationExceptionHandler(OneTimeCodeVerificationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
