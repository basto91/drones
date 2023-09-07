package com.musala.drone.drone.configuration;

import com.musala.drone.drone.util.converter.ModelEnumConverter;
import com.musala.drone.drone.util.converter.StateEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ModelEnumConverter());
        registry.addConverter(new StateEnumConverter());
    }
}
