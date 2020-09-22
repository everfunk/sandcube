package ru.mindb8.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TomcatConfig {

    @Bean
    Tomcat tomcat() {
        val tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setSilent(false);
        tomcat.setBaseDir(".");
        return tomcat;
    }

    private final WebApplicationContext rootContext;

    @Bean
    public ServletContext servletContext() {
        return new ApplicationContext((StandardContext)apacheContainer());
    }

    @Bean
    public Context apacheContainer() {
        return tomcat().addContext("", null);
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(rootContext);
        Tomcat.addServlet(apacheContainer(), "dispatcher", dispatcherServlet);
        apacheContainer().addServletMappingDecoded("/*", "dispatcher");
        return dispatcherServlet;
    }

    @SneakyThrows
    @EventListener(ContextStartedEvent.class)
    private void startEventHandler() {
        tomcat().getConnector();
        tomcat().start();
    }
}
