package com.africaprudential.backenddemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public ResponseEntity<?> User(Authentication auth) {
        OAuth2Authentication oauth = (OAuth2Authentication) auth;

//        Map userDetails = new HashMap();
//        User user = userService.findByUsername(auth.getName()).get();
//
//        userDetails.put("userId", user.getId());
//
//        oauth.setDetails(userDetails);

        //System.out.println(oauth);
        return ResponseEntity.ok(oauth);
    }
}
