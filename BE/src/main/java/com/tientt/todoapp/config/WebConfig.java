package com.tientt.todoapp.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.tientt.todoapp.dto.TaskDTO;
import com.tientt.todoapp.dto.UserDTO;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    // @Override
    // public void configureMessageConverters(List<HttpMessageConverter<?>>
    // converters) {
    // ObjectMapper objectMapper = new ObjectMapper();
    // objectMapper.setPropertyNamingStrategy(SnakeCaseStrategy.INSTANCE);
    // MappingJackson2HttpMessageConverter converter = new
    // MappingJackson2HttpMessageConverter();
    // converter.setObjectMapper(objectMapper);
    // converters.add(converter);
    // }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return objectMapper;
    }

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}