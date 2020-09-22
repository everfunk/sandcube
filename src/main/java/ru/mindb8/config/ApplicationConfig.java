package ru.mindb8.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;
import ru.mindb8.controller.MainController;

@Import({TomcatConfig.class, WebConfig.class})
@RequiredArgsConstructor
public class ApplicationConfig   {

    private final WebApplicationContext webApplicationContext;

    @Bean
    ObjectMapper objectMapper() {
        val mapper = new ObjectMapper();
        mapper.setVisibility(mapper.getDeserializationConfig()
                .getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
        return mapper;
    }

    @Bean
    MainController mainController() {
        return new MainController(objectMapper());
    }
}
