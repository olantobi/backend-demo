/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.event;

import com.africaprudential.backenddemo.model.SmsNotification;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author olanrewaju.ebenezer
 */
public class SmsEvent extends ApplicationEvent {
    @Getter
    private SmsNotification sms;
    
    public SmsEvent(SmsNotification sms) {
        super(sms);
        this.sms = sms;
    }
    
}
