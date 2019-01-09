/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.event;

import com.boardmanbet.userservice.model.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 *
 * @author olanrewaju.ebenezer
 */
public class PasswordChangedEvent extends ApplicationEvent {

    private Locale locale;
    private User user;

    public PasswordChangedEvent(User user) {
        super(user);
        this.user = user;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }      
}
