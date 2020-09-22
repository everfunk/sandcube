package ru.mindb8;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import ru.mindb8.config.ApplicationConfig;

public class SandCubeMain {
    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ApplicationConfig.class);
        context.registerShutdownHook();
        context.refresh();
        context.start();
    }
}
