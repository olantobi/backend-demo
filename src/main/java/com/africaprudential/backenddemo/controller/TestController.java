package com.africaprudential.backenddemo.controller;

import com.africaprudential.backenddemo.event.EmailEvent;
import com.africaprudential.backenddemo.event.SmsEvent;
import com.africaprudential.backenddemo.model.EmailNotification;
import com.africaprudential.backenddemo.model.SmsNotification;
import com.africaprudential.backenddemo.model.Test;
import com.africaprudential.backenddemo.service.BusinessLogicService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/v1/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final ApplicationEventPublisher eventPublisher;
    private final BusinessLogicService businessLogicService;

    @GetMapping
    @ApiOperation(nickname = "/test", value = "Get all tests")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful")
    })
    public ResponseEntity<?> getAllTests(Principal principal) {
        String username = principal.getName();

        List<Test> test = null;

        try {
            test = businessLogicService.getAllTest(username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        SmsNotification sms = new SmsNotification("08023323322", "Sms message");

        try {
            System.out.println("publishing sms notification");
            eventPublisher.publishEvent(new SmsEvent(sms));
        } catch (Exception me) {
            throw new InternalError("Error while attempting to publish sms notification: "+me);
        }

        EmailNotification email = new EmailNotification("tobi@yahoo.com", "Test Email",  "This is a test email");
        try {
            System.out.println("Publishing email notification");
            eventPublisher.publishEvent(new EmailEvent(email));
        } catch (Exception me) {
            throw new InternalError("Error while attempting to publish email notification: "+me);
        }

        return ResponseEntity.ok(test);
    }

    @PostMapping
    @ApiOperation(nickname = "/test", value = "Create new Test")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public ResponseEntity<?> createTest(Principal principal, @RequestBody Test test) {
        String username = principal.getName();

        try {
            businessLogicService.createTest(test, username);
        } catch (Exception e) {
            ResponseEntity.badRequest().build();
        }

        SmsNotification sms = new SmsNotification("08023323322", "Sms message");
        eventPublisher.publishEvent(new SmsEvent(sms));

        EmailNotification email = new EmailNotification("tobi@yahoo.com", "Test Email",  "This is a test email");
        eventPublisher.publishEvent(new EmailEvent(email));

        return ResponseEntity.ok("Successful");
    }

}
