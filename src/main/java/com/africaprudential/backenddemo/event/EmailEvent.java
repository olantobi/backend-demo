/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.event;

import com.africaprudential.backenddemo.model.EmailNotification;

/**
 *
 * @author olanrewaju.ebenezer
 */

public class EmailEvent {

    private final EmailNotification email;

    public EmailEvent(EmailNotification email) {
        this.email = email;
    }

    public EmailNotification getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "EmailEvent{" +
                "email=" + email +
                '}';
    }
}
