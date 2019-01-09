package com.africaprudential.backenddemo.service.impl;

import com.africaprudential.backenddemo.model.User;
import com.africaprudential.backenddemo.service.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccessControlServiceImpl implements AccessControlService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean authorize(String username, String password) {

        System.out.println("Making remote call to authorize user");
        return true;
    }

    @Override
    public User findByUsername(String username) {
        System.out.println("Making remote call to get user object by username");
        return new User(username, "password", "ROLE_SUPERADMIN");
    }
}
