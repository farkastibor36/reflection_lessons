package org.example.springbootpractice;

import org.example.springbootpractice.mapper.ElementMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ElementMapper elementMapper() {
        return Mappers.getMapper(ElementMapper.class);
    }
}