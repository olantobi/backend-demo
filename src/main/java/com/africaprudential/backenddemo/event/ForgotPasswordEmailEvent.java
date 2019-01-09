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
public class ForgotPasswordEmailEvent extends ApplicationEvent {
    private String appUrl;    
    private User user;
    private TokenClass tokenType;
    
    
    public ForgotPasswordEmailEvent(User user, TokenClass tokenType) {
        super(user);
        
        this.user = user;                
        this.tokenType = tokenType;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }   

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TokenClass getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenClass tokenType) {
        this.tokenType = tokenType;
    }
}
