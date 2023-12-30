package com.arawakanda.springmultiplesmtpmailserver.configurationproperties;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "mail")
@ConfigurationPropertiesScan
public class MailListProperties {

    private Map<String, MailProperties> list;

    public MailListProperties(Map<String, MailProperties> list) {
        this.list = list;
    }

    public Map<String, MailProperties> getList() {
        return list;
    }

    public void setList(Map<String, MailProperties> list) {
        this.list = list;
    }

}
