package com.ptk.demo.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        Path uploadPath = Paths.get("uploads/products")
//                .toAbsolutePath()
//                .normalize();

        registry.addResourceHandler("/uploads/products/**")
        			.addResourceLocations("file:uploads/products/");
    }
}