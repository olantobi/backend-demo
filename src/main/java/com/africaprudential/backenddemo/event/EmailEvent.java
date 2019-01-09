/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.event;

import com.africaprudential.backenddemo.model.EmailNotification;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author olanrewaju.ebenezer
 */

public class EmailEvent extends ApplicationEvent {

    @Getter
    private EmailNotification email;

    public EmailEvent(EmailNotification email) {
        super(email);
        this.email = email;
    }
}
