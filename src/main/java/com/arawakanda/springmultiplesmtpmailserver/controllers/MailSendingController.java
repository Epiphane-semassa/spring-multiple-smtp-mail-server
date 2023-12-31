package com.arawakanda.springmultiplesmtpmailserver.controllers;

import com.arawakanda.springmultiplesmtpmailserver.payload.SendEmailData;
import com.arawakanda.springmultiplesmtpmailserver.services.BetaEmailSenderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * >>>>>>>> MAKED BY DE XAAG <<<<<<<<<
 */


@RestController
@RequestMapping("/api/mails")
public class MailSendingController {

    private final BetaEmailSenderService betaEmailSenderService;

    public MailSendingController(BetaEmailSenderService betaEmailSenderService) {
        this.betaEmailSenderService = betaEmailSenderService;
    }

    @PostMapping("send-email")
    public String sendEmail(@RequestBody SendEmailData sendEmailData) {
        betaEmailSenderService.sendEmail(sendEmailData);
        return "Mail Sent !!";
    }


}


