/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.listeners;

import com.boardmanbet.userservice.dto.EmailResponse;
import com.boardmanbet.userservice.email.EmailService;
import com.boardmanbet.userservice.event.ForgotPasswordEmailEvent;
import com.boardmanbet.userservice.model.User;
import com.boardmanbet.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 *
 * @author olanrewaju.ebenezer
 */
@Component
@Slf4j
public class ForgotPasswordEmailListener {   

    @Autowired
    private UserService userService;
    @Value("${message.forgotPassword}")
    private String EMAIL_MESSAGE;
    @Value("${EMAIL_SENDER_EMAIL}")
    private String emailSenderEmail;
    @Value("${EMAIL_SENDER_NAME}")
    private String emailSenderName;
    
    @Autowired    
    private EmailService mailService;

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void publishEvent(ForgotPasswordEmailEvent event) {

        User user = event.getUser();

        String token = UUID.randomUUID().toString();

        userService.createPasswordResetTokenForUser(user, token);
        
        String subject = "Password Reset Request";
        String tokenUrl = token;
        String emailText = EMAIL_MESSAGE
                    .replaceFirst("##userFirstName##", user.getFirstName())                    
                    .replaceFirst("##tokenUrl##", tokenUrl);
        try {
            EmailResponse response = mailService.send(emailSenderName, emailSenderEmail, user.getFirstName(), user.getEmail(), subject, emailText);
            log.info("Mail Response: "+response.getStatus()+": "+response.getMessage());  

        } catch (Exception ex) {
            log.error("MailjetException: "+ex.getMessage());
        } 

    }

}
