package ru.mindb8.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mindb8.dto.ResponseDto;
import ru.mindb8.dto.SimpleDto;

@RequestMapping
@RequiredArgsConstructor
public class MainController {

    final private ObjectMapper objectMapper;

    @PostMapping(path = "/request/{param}")
    @ResponseBody
    ResponseEntity<ResponseDto> request(@PathVariable("param") String param, @RequestBody SimpleDto body) {
        return ResponseEntity.ok().<ResponseDto>body(new ResponseDto("Ok! " +  body.getId() + " " + param));
    }
}
