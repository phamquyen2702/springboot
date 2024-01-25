package com.example.demo2.controller;

import com.example.demo2.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api")
public class MailController {
    public static final Logger logger = LoggerFactory.getLogger(MailController.class);
    @Autowired
    private MailService notificationService;

    @GetMapping("/send-mail")
    public String send() {
        try {
            notificationService.sendEmail();
        } catch (MailException mailException) {
            logger.error("Can't send mail", mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }

    @GetMapping("/send-mail-attachment")
    public String sendWithAttachment() throws MessagingException {

        try {
            notificationService.sendEmailWithAttachment();
        } catch (MailException mailException) {
            logger.error("Can't send mail", mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }
}