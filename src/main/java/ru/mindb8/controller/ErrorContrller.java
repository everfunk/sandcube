package ru.mindb8.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorContrller {
    @ExceptionHandler
    ResponseEntity<String> handler(Exception exception) {
        log.error("{}", exception);
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
