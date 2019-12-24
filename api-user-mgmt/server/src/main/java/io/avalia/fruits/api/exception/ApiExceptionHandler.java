package io.avalia.fruits.api.exception;

import io.avalia.fruits.api.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = Logger.getLogger(ApiExceptionHandler.class.getSimpleName());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        LOG.log(Level.SEVERE, e.getMessage(), e);
        return new ResponseEntity<>(ApiError.INTERNAL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ApiError.NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException() {
        return new ResponseEntity<>(ApiError.AUTHENTICATION, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InactiveException.class)
    public ResponseEntity<ApiError> handleInactiveException() {
        return new ResponseEntity<>(ApiError.INACTIVE, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException() {
        return new ResponseEntity<>(ApiError.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }
}
