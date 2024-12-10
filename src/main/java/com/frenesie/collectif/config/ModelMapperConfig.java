package com.frenesie.collectif.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        
        // Configuration personnalisée si nécessaire
        modelMapper.getConfiguration()
            .setSkipNullEnabled(true)  // Ignorer les propriétés nulles lors du mapping
            .setFieldMatchingEnabled(true);  // Autoriser le matching des champs privés
        
        return modelMapper;
    }
}