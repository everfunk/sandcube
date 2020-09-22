package ru.mindb8.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.ServletContext;


public class WebConfig
        extends WebMvcConfigurationSupport
{

    @Autowired
    public WebConfig(ServletContext context) {
        super();
        setServletContext(context);
    }

//    @Override
//    public void configureMessageConverters(
//            List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        converters.add(new MappingJackson2HttpMessageConverter());
//    }
}
