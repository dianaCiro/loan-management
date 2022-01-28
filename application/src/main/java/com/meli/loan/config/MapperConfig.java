package com.meli.loan.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Model mapper configuration.
 */
@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }
}
