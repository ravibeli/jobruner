package com.poller.jobruner.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    ObjectMapper mapper = new ObjectMapper();
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        response.put("errorCode", "constraint.violation");
        response.put("httpStatus", HttpStatus.BAD_REQUEST.name());
        response.put("message", e.getLocalizedMessage());
        return new ResponseEntity<>(mapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        response.put("errorCode", "constraint.violation");
        response.put("httpStatus", HttpStatus.BAD_REQUEST.name());
        response.put("message", e.getLocalizedMessage());
        return new ResponseEntity<>(mapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
    }
}
