package com.africaprudential.backenddemo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/api/v1/test")
@RestController
public class TestController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping
    @ApiOperation(value = "/test", nickname = "/stest")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 404, message = "Record not found")
    })
    public ResponseEntity<?> testEndpoint(Principal principal) {
        String username = principal.getName();

        AuditDto audit = new AuditDto("User", "createUser", "Created a new user with details: " + new TokenUserDto(createdUser), "");
        eventPublisher.publishEvent(new AuditTrailEvent(audit, authHeader, ipAddress));
        return ResponseEntity.ok("");
    }
}
