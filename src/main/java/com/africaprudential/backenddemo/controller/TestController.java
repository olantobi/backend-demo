package com.africaprudential.backenddemo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/api/v1/test")
@RestController
public class TestController {

    @GetMapping
    @ApiOperation(value = "/test", nickname = "/stest")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 404, message = "Record not found")
    })
    public ResponseEntity<?> testEndpoint(Principal principal) {
        String username = principal.getName();


        return ResponseEntity.ok("");
    }
}
