/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.listeners;

import com.africaprudential.backenddemo.event.SmsEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author olanrewaju.ebenezer
 */
@Component
public class SmsNotificationListener {

    @Async
    @EventListener
    public void sendForgotPasswordSms(SmsEvent event) {
        System.out.println("Sending sms ..."+event.getSms());
    }
}
