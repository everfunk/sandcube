package ru.mindb8.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import ru.mindb8.controller.ErrorController;

import javax.servlet.ServletContext;


public class WebConfig
        extends WebMvcConfigurationSupport
{

    @Autowired
    public WebConfig(ServletContext context) {
        super();
        setServletContext(context);
    }

    @Bean
    SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        return new ErrorController();
    }

//    @Override
//    public void configureMessageConverters(
//            List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        converters.add(new MappingJackson2HttpMessageConverter());
//    }
}
