package com.arawakanda.springmultiplesmtpmailserver.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DynamicBeanDefinitionRegistrarConfiguration {
    @Bean
    public static DynamicBeanDefinitionRegistrar beanDefinitionRegistrar(Environment environment) {
        return new DynamicBeanDefinitionRegistrar(environment);
    }
}
