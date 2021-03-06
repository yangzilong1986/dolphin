package com.sabsari.dolphin.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig  {

    @Value("${mail.host}") private String mailHost;
    @Value("${mail.port}") private int mailPort;
    @Value("${mail.username}") private String mailUserName;
    @Value("${mail.password}") private String mailPassword;

    @Bean
    public JavaMailSenderImpl mailSender() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(mailHost);
        javaMailSender.setPort(mailPort);
        javaMailSender.setUsername(mailUserName);
        javaMailSender.setPassword(mailPassword);

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth","true");
        properties.setProperty("mail.smtp.starttls.enable","true");
        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }
}
