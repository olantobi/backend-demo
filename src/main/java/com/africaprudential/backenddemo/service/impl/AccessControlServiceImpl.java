package com.africaprudential.backenddemo.service.impl;

import com.africaprudential.backenddemo.model.User;
import com.africaprudential.backenddemo.model.UserPrincipal;
import com.africaprudential.backenddemo.service.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccessControlServiceImpl implements AccessControlService {

    @Autowired
    @Qualifier("pbkdf2PasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        System.out.println("Making remote call to get user object by username");
        User user = new User(username, passwordEncoder.encode("P@ssw0rd"), "ROLE_ADMIN");
        user.setEnabled(true);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = findByUsername(username);
        return UserPrincipal.create(user);
    }
}
