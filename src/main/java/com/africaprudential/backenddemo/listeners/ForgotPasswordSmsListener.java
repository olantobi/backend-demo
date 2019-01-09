/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.listeners;

import com.boardmanbet.userservice.event.ForgotPasswordSmsEvent;
import com.boardmanbet.userservice.model.User;
import com.boardmanbet.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

/**
 *
 * @author olanrewaju.ebenezer
 */
@Component
@Slf4j
public class ForgotPasswordSmsListener {    
    
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${sms.forgotPassword}")
    private String SMS_MESSAGE;
    @Value("${sms.senderId}")
    private String smsSenderId;
    @Value("${SMS_PORTAL_USERNAME}")
    private String smsPortalUsername;
    @Value("${SMS_PORTAL_PASSWORD}")
    private String smsPortalPassword;
    
    @Async("threadPoolTaskExecutor")
    @EventListener
    public void sendForgotPasswordSms(ForgotPasswordSmsEvent event) {
        try {           
            User user = event.getUser();
            
            String token = UUID.randomUUID().toString();
        
            userService.createPasswordResetTokenForUser(user, token);        

            String tokenUrl = token;                                
            
            String smsText = SMS_MESSAGE
                    .replaceFirst("##userFirstName##", user.getFirstName())                    
                    .replaceFirst("##tokenUrl##", tokenUrl).replaceAll("#", "%23").replaceAll(" ", "+");
            
            String uriString = "http://www.smsportalgateway.com/components/com_spc/smsapi.php?username="+smsPortalUsername+
                    "&password="+smsPortalPassword+"&sender="+smsSenderId+"&recipient="+user.getMobileNumber()+"&message="+smsText;
                                    
            URI uri = new URI(uriString);
            
            String response = restTemplate.getForObject(uri, String.class);
            
            //logger.info("URL: "+uriString);
            log.info("Forgot Password SMS Response: "+response);                                                          
            
        } catch (Exception ex) {            
            log.error("Exception", ex.getMessage());            
        }
    }
}
