/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.listeners;

import com.boardmanbet.userservice.dto.EmailResponse;
import com.boardmanbet.userservice.email.EmailService;
import com.boardmanbet.userservice.event.PasswordChangedEvent;
import com.boardmanbet.userservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author olanrewaju.ebenezer
 */
@Component
@Slf4j
public class PasswordChangedEventListener {    

    @Value("${message.passwordChanged}")
    private String EMAIL_MESSAGE;
    @Value("${EMAIL_SENDER_EMAIL}")
    private String emailSenderEmail;
    @Value("${EMAIL_SENDER_NAME}")
    private String emailSenderName;
    @Autowired    
    private EmailService mailService;

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void publishEvent(PasswordChangedEvent event) {

        User user = event.getUser();
        String subject = "Password Reset Request";
        String emailText = EMAIL_MESSAGE
                .replaceFirst("##userFirstName##", user.getFirstName());

        try {
            EmailResponse response = mailService.send(emailSenderName, emailSenderEmail, user.getFirstName(), user.getEmail(), subject, emailText);
            log.info("Mail Response: "+response.getStatus()+": "+response.getMessage());  

        } catch (Exception ex) {
            log.error("MailjetException: "+ex.getMessage());
        } 
    }
}
