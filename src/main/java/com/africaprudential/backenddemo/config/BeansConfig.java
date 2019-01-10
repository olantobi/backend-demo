package com.africaprudential.backenddemo.config;

import com.africaprudential.backenddemo.encoder.CustomPasswordEncoder;
import com.africaprudential.backenddemo.service.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansConfig {

    @Autowired
    private AccessControlService accessControlService;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PasswordEncoder pbkdf2PasswordEncoder() {
        return new CustomPasswordEncoder();
    }

    @Bean
    @Primary
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(accessControlService);
        authProvider.setPasswordEncoder(pbkdf2PasswordEncoder());
        return authProvider;
    }
}
