package com.arawakanda.springmultiplesmtpmailserver.services;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
public class AlphaMailSenderService {

    @Autowired
    @Qualifier(value = "first")
    private JavaMailSenderImpl firstMailSender;

    /*@Autowired
    @Qualifier(value = "second")
    private JavaMailSenderImpl secondMailSender;
*/
    @Autowired
    private List<JavaMailSenderImpl> mailSenders;


    //@PostConstruct
    public void init() {
        try {
            String toEmail = "semassaepiphane@gmail.com";
            testMail(firstMailSender, toEmail, "");
            //testMail(secondMailSender, toEmail, "");
        } catch (MessagingException | IOException e) {
            log.error("AlphaMailSenderService::init ##---->> {}", e.getMessage());
            try {
                throw new Exception(e.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void testMail(JavaMailSenderImpl javaMailSender, String toEmail, String fromAddress) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(toEmail);
        helper.setText("Sending. Hello test mail multiple smtp servers.", false);
        helper.setSubject("Test Sending Mail");
        if (fromAddress == null || fromAddress.isBlank() || fromAddress.isEmpty()) {
            fromAddress = javaMailSender.getJavaMailProperties().getProperty("mail.from-address");
        }
        helper.setFrom(fromAddress, "MSMTP-SERVER");
        helper.setValidateAddresses(true);
        javaMailSender.send(message);

        System.out.println("MAIL TEST SENDING SUCCESSFULLY FROM " + fromAddress);
    }

}
