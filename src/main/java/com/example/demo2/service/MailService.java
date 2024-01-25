package com.example.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail() throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo("minamino585584@gmail.com");
        mail.setSubject("Testing Mail API");
        mail.setText("Hurray ! You have done that dude...");
        javaMailSender.send(mail);
    }

    public void sendEmailWithAttachment() throws MailException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("minamino585584@gmail.com");
        helper.setSubject("Testing Mail API with Attachment");
        helper.setText("Please find the attached document below.");


        ClassPathResource classPathResource = new ClassPathResource("att.txt");
        helper.addAttachment(classPathResource.getFilename(), classPathResource);

        javaMailSender.send(message);
    }

}