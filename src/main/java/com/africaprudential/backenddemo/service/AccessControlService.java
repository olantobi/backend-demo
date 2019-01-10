package com.africaprudential.backenddemo.service;

import com.africaprudential.backenddemo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccessControlService extends UserDetailsService {
    //boolean authorize(String username, String password);
    User findByUsername(String username);
}
