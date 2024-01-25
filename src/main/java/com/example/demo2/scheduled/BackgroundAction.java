package com.example.demo2.scheduled;

import com.example.demo2.service.IUserService;
import com.example.demo2.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BackgroundAction {
    private static final Logger log = LoggerFactory.getLogger(BackgroundAction.class);
    @Autowired
    MailService mailService;
    @Scheduled(fixedRate = 10000000)
    public void logDemo(){
        try {
            mailService.sendEmail();
            log.info("Sent to mimino585584@gmail.com");
        } catch (MailException mailException) {
            log.error("Can't send mail", mailException);
        }

    }
    @Scheduled(cron = "0 0 18 * * MON-FRI")
    public void task2(){
        log.info("Hẹn gặp lại");
    }

    @Async()
    public void task3(){
        log.info("Hẹn gặp lại");
    }
}
