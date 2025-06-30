package br.edu.bsi.trocaverso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // permite para todos os endpoints
                .allowedOrigins("http://localhost:4200") // origem do Angular
                .allowedMethods("*") // permite todos os métodos HTTP
                .allowedHeaders("*") // permite todos os cabeçalhos
                .allowCredentials(true); // permite envio de cookies/autenticação
    }
}

