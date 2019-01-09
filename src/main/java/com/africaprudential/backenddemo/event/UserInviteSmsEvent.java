/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.event;

import com.boardmanbet.userservice.enums.TokenClass;
import com.boardmanbet.userservice.model.User;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author olanrewaju.ebenezer
 */
public class UserInviteSmsEvent extends ApplicationEvent {
    private User user;
    private TokenClass tokenClass;

    public UserInviteSmsEvent(User user, TokenClass tokenClass) {
        super(user);
        this.user = user;
        this.tokenClass = tokenClass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TokenClass getTokenClass() {
        return tokenClass;
    }

    public void setTokenClass(TokenClass tokenClass) {
        this.tokenClass = tokenClass;
    }
    
}
