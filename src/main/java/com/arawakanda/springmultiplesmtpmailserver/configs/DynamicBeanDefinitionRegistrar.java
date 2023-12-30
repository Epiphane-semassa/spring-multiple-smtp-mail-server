package com.arawakanda.springmultiplesmtpmailserver.configs;

import com.arawakanda.springmultiplesmtpmailserver.configurationproperties.MailListProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class DynamicBeanDefinitionRegistrar implements BeanDefinitionRegistryPostProcessor {

    private static MailListProperties mailListProperties;
    private static final String PROPERTIES_PREFIX = "mail";

    public DynamicBeanDefinitionRegistrar(Environment environment) {
        mailListProperties = Binder
                .get(environment)
                .bind(PROPERTIES_PREFIX, MailListProperties.class)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        mailListProperties.getList().forEach((beanName, mailProperties) -> {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setBeanClass(JavaMailSenderImpl.class);
            definition.setInstanceSupplier(() -> {
                JavaMailSenderImpl sender = new JavaMailSenderImpl();
                sender.setProtocol(mailProperties.getProtocol());
                sender.setHost(mailProperties.getHost());
                sender.setPort(mailProperties.getPort());
                sender.setUsername(mailProperties.getUsername());
                sender.setPassword(mailProperties.getPassword());
                Properties properties = new Properties();
                mailProperties.getProperties().forEach(properties::setProperty);
                sender.setJavaMailProperties(properties);
                return sender;
            });
            registry.registerBeanDefinition(beanName, definition);
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

}
