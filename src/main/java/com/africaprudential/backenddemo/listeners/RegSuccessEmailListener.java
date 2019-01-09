/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.listeners;

import com.boardmanbet.userservice.dto.EmailResponse;
import com.boardmanbet.userservice.email.EmailService;
import com.boardmanbet.userservice.event.RegSuccessEmailEvent;
import com.boardmanbet.userservice.model.User;
import com.boardmanbet.userservice.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 *
 * @author olanrewaju.ebenezer
 */
@Component
@Slf4j
public class RegSuccessEmailListener {    

    @Value("${EMAIL_SENDER_EMAIL}")
    private String emailSenderEmail;
    @Value("${EMAIL_SENDER_NAME}")
    private String emailSenderName;
    private String mailTemplate;
    @Value("${APP_URL}")
    private String APP_URL;
    @Autowired    
    private EmailService mailService;

    @PostConstruct
    private void readTemplate() {
        try {
            mailTemplate = Utils.readLineByLine("mail_templates/email_complete.htm");
        } catch (IOException ex) {
            log.error("IOException: "+ex.getMessage());
        }
    }

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void sendRegistrationSuccessEmail(RegSuccessEmailEvent event) {

        User user = event.getUser();
        String subject = "Welcome to Boardman Bets";

        try {                                    
            if (user.getFirstName() == null)
                user.setFirstName("");
                           
            String emailText = null;
            
            emailText = mailTemplate.replace("##userFirstName##", user.getFirstName()).replace("##appUrl##", APP_URL);            
            
            EmailResponse response = mailService.send(emailSenderName, emailSenderEmail, user.getFirstName(), user.getEmail(), subject, emailText);
            log.info("Mail Response: "+response.getStatus()+": "+response.getMessage());

        } catch (Exception ex) {
            log.error("MailjetException: "+ex.getMessage());
        } 
    }
}
