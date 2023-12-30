package com.arawakanda.springmultiplesmtpmailserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.arawakanda.springmultiplesmtpmailserver.configurationproperties")
public class SpringMultipleSmtpMailServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMultipleSmtpMailServerApplication.class, args);
    }

}
