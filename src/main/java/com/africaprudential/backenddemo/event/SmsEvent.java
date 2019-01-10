/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.event;

import com.africaprudential.backenddemo.model.SmsNotification;

/**
 *
 * @author olanrewaju.ebenezer
 */
public class SmsEvent {
    private final SmsNotification sms;
    
    public SmsEvent(SmsNotification sms) {
        this.sms = sms;
    }

    public SmsNotification getSms() {
        return sms;
    }

    @Override
    public String toString() {
        return "SmsEvent{" +
                "sms=" + sms +
                '}';
    }
}
