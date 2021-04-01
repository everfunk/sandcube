package ru.mindb8.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import ru.mindb8.dto.ErrorDto;

@Slf4j
@ControllerAdvice
public class ErrorController extends SimpleMappingExceptionResolver {
    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorDto> handler(Exception exception) {
        log.error("{}", exception.getMessage(), exception);
        return ResponseEntity.badRequest().body(new ErrorDto(exception.getMessage()));
    }
}
