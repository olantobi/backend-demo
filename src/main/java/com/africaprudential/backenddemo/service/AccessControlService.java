package com.africaprudential.backenddemo.service;

public interface AccessControlService {
    boolean authorize(String username, String password);
}
