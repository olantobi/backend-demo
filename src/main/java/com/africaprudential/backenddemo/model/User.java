package com.africaprudential.backenddemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String name;
    private String email;
    private boolean enabled;
    private String encryptedPassword;
    private String roleName;

    public User(String username, String password, String roleName) {
        this.username = username;
        this.encryptedPassword = password;
        this.roleName = roleName;
    }
}
