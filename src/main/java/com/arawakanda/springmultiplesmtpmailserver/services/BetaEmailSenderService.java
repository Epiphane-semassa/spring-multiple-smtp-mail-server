package com.arawakanda.springmultiplesmtpmailserver.services;

import com.arawakanda.springmultiplesmtpmailserver.payload.SendEmailData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Properties;

/***
 * --------------->>>>>>>> MAKED BY DE XAAG <<<<<<<<<<<<<<<-------------
 */

@Service
@Slf4j
public class BetaEmailSenderService {

    private final AlphaMailSenderService alphaMailSenderService;

    public BetaEmailSenderService(AlphaMailSenderService alphaMailSenderService) {
        this.alphaMailSenderService = alphaMailSenderService;
    }


    public void sendEmail(SendEmailData sendEmailData) {

        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setProtocol(sendEmailData.getProtocol());
            mailSender.setHost(sendEmailData.getHost());
            mailSender.setPort(sendEmailData.getPort());
            mailSender.setUsername(sendEmailData.getUsername());
            mailSender.setPassword(sendEmailData.getPassword());
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", String.valueOf(sendEmailData.isAuthEnabled()));
            properties.setProperty("mail.smtp.starttls.enable", String.valueOf(sendEmailData.isTlsEnabled()));
            properties.setProperty("mail.smtp.ssl.enable", String.valueOf(sendEmailData.isSslEnabled()));
            properties.setProperty("mail.smtp.connectiontimeout", String.valueOf(60000));
            properties.setProperty("mail.smtp.timeout", String.valueOf(60000));
            properties.setProperty("mail.smtp.writetimeout", String.valueOf(60000));
            mailSender.setJavaMailProperties(properties);
            alphaMailSenderService.testMail(mailSender, sendEmailData.getToEmail(), sendEmailData.getFromAddress());
        } catch (Exception e) {
            log.error("BetaEmailSenderService::sendEmail ------>>>  {}", e.getMessage());
            try {
                throw new Exception(e.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
