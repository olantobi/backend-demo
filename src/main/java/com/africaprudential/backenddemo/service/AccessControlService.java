package com.africaprudential.backenddemo.service;

import com.africaprudential.backenddemo.model.User;

public interface AccessControlService {
    boolean authorize(String username, String password);
    User findByUsername(String username);
}
