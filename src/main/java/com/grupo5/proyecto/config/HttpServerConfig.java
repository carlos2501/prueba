package com.grupo5.proyecto.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * HttpServerConfig configura un puerto HTTP adicional al proporcionado por Spring Boot.
 * Así el servidor puede comunciarse mediante protocolos HTTP (Puerto 8080) y HTTPS (Puerto 8443)
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */

@Configuration
@Component
public class HttpServerConfig {
    @Bean
    public ServletWebServerFactory servletContainer(@Value("${server.http.port}") int httpPort) {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setPort(httpPort);

        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }
}