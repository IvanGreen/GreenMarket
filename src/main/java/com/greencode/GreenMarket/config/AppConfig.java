package com.greencode.GreenMarket.config;

import com.fasterxml.jackson.core.io.IOContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan("com.greencode.GreenMarket")
@Configuration
public class AppConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/images/**")) {
            registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
        }

    }


}
