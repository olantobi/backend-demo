/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.listeners;

import com.africaprudential.backenddemo.event.EmailEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author olanrewaju.ebenezer
 */
@Component
public class EmailNotificationListener {

    @Async("threadPoolTaskExecutor")
    @EventListener
    public void sendInvitationEmail(EmailEvent event) {
        System.out.println("Sending email event ..."+event.getEmail());

    }
}
