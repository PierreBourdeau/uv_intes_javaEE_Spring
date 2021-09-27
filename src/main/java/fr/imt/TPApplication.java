package fr.imt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TPApplication {

    public static void main(String[] args) {
        SpringApplication.run(TPApplication.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowCredentials(true)
                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE")
                        .allowedOrigins("http://localhost:4200");
            }
        };
    }
}
