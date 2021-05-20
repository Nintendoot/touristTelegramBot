package by.nintendo.touristtelegrambot.controller;

import by.nintendo.touristtelegrambot.exception.CityAlreadyExistsException;
import by.nintendo.touristtelegrambot.exception.CityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> err = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            err.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.error("ArgumentNotValid : " + e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CityAlreadyExistsException.class)
    public ResponseEntity<Object> cityAlreadyExists(CityAlreadyExistsException e) {
        log.error("CityAlreadyExistsException :" + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<Object> cityAlreadyExists(CityNotFoundException e) {
        log.error("CityNotFoundException : " + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
