package com.africaprudential.backenddemo.security;

import com.africaprudential.backenddemo.service.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessControlService authorizationService;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                        .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email"));

        return UserPrincipal.create(user);
    }

    public UserDetails loaduserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: "+id));

        return UserPrincipal.create(user);
    }
}
