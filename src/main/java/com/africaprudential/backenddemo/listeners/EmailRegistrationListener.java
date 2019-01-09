/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.listeners;

import com.boardmanbet.userservice.dto.EmailResponse;
import com.boardmanbet.userservice.email.EmailService;
import com.boardmanbet.userservice.event.UserInviteEmailEvent;
import com.boardmanbet.userservice.model.User;
import com.boardmanbet.userservice.service.UserService;
import com.boardmanbet.userservice.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.security.SecureRandom;

/**
 *
 * @author olanrewaju.ebenezer
 */
@Component
@Slf4j
public class EmailRegistrationListener {    

    @Autowired
    private UserService userService;
    @Value("${EMAIL_SENDER_EMAIL}")
    private String senderEmail;
    @Value("${EMAIL_SENDER_NAME}")
    private String senderName;
    private String mailTemplate;
    
    @Autowired    
    private EmailService mailService;

    @Autowired
    private RestTemplate restTemplate;
    @Value("${sms.invitemessage}")
    private String SMS_MESSAGE;
    @Value("${sms.senderId}")
    private String smsSenderId;
    @Value("${SMS_PORTAL_USERNAME}")
    private String smsPortalUsername;
    @Value("${SMS_PORTAL_PASSWORD}")
    private String smsPortalPassword;

    @PostConstruct
    private void readTemplate() {
        try {
            mailTemplate = Utils.readLineByLine("mail_templates/email.htm");
        } catch (IOException ex) {
            log.error("IOException: "+ex.getMessage());
        }
    }
    
    @Async("threadPoolTaskExecutor")
    @EventListener
    public void sendInvitationEmail(UserInviteEmailEvent event) {
        System.out.println("Sending invitation email to ...");
        User user = event.getUser();
        System.out.println(user);
        String token = "";
        SecureRandom sec = new SecureRandom();
        sec.setSeed(System.currentTimeMillis());
        int secValue;

        if (null != event.getTokenType()) {
            switch (event.getTokenType()) {
                case NEW:
                case EXPIRED:
                case NO_TOKEN:
                    secValue = sec.nextInt(99999);
                    token = Utils.leftZeroPad(String.valueOf(secValue), 5);
                    userService.createVerificationToken(user.getId(), token);
                    break;
                case RESEND:
                    token = user.getVerificationToken();
                    break;
                default:
                    break;
            }
        }

        try {
            String subject = "Complete your Registration";
            
            if (user.getFirstName() == null)
                user.setFirstName("");
                           
            String emailText = null;
            
            emailText = mailTemplate.replace("##tokenCode##", token).replace("##userFirstName##", user.getFirstName());
            
            EmailResponse response = mailService.send(senderName, senderEmail, user.getFirstName(), user.getEmail(), subject, emailText);
            
            //MailjetResponse response = Utils.mailJetApi(senderName, senderEmail, user.getFirstName(), user.getEmail(), subject, emailText, MJ_APIKEY_PUBLIC, MJ_APIKEY_PRIVATE);           
            log.info("Email response: "+response.getStatus()+" "+response.getMessage());

             //SMS LOGIC
            String smsText = SMS_MESSAGE+" "+token;

            String url = "https://account.kudisms.net/api/?username={username}&password={password}&sender={senderId}&message={smsText}&mobiles={recipient}";
            URI expandedUri = new UriTemplate(url).expand(smsPortalUsername, smsPortalPassword, smsSenderId, smsText, user.getMobileNumber());
            log.info("SMS: "+expandedUri.getQuery());
            
            url = URLDecoder.decode(expandedUri.toString(), "UTF-8"); // java.net class
            //uriString = URLEncoder.encode(uriString, "UTF-8");
            String textResponse = restTemplate.getForObject(url, String.class);
            
            log.info("Invitation SMS Response: "+textResponse);

        } catch (Exception ex) {
            log.error("Exception: "+ex.getMessage());
        } 
    }
}
