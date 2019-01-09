/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.africaprudential.backenddemo.event;

import com.boardmanbet.userservice.dto.AuditDto;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author olanrewaju.ebenezer
 */
public class AuditTrailEvent extends ApplicationEvent {
    private AuditDto auditDto;
    private String authHeader;
    private String ipAddress;
    
    public AuditTrailEvent(AuditDto auditDto, String authHeader, String ipAddress) {
        super(auditDto);
        this.auditDto = auditDto;
        this.authHeader = authHeader;
        this.ipAddress = ipAddress;
    }

    public AuditDto getAuditDto() {
        return auditDto;
    }

    public void setAuditDto(AuditDto auditDto) {
        this.auditDto = auditDto;
    }

    public String getAuthHeader() {
        return authHeader;
    }

    public void setAuthHeader(String authHeader) {
        this.authHeader = authHeader;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }           
    
}
