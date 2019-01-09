package com.africaprudential.backenddemo.controller;

import com.africaprudential.backenddemo.model.EmailNotification;
import com.africaprudential.backenddemo.model.SmsNotification;
import com.africaprudential.backenddemo.model.Test;
import com.africaprudential.backenddemo.service.AccessControlService;
import com.africaprudential.backenddemo.service.BusinessLogicService;
import com.africaprudential.backenddemo.service.WorkflowService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/api/v1/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final ApplicationEventPublisher eventPublisher;
    private final BusinessLogicService businessLogicService;
    private final WorkflowService workflowService;

    @GetMapping
    @ApiOperation(value = "/test", nickname = "/test")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 404, message = "Record not found")
    })
    public ResponseEntity<?> testEndpoint(Principal principal, @RequestBody Test test) {
        String username = principal.getName();

        workflowService.confirmWorkflow(username);

        businessLogicService.testService(test);

        SmsNotification sms = new SmsNotification("08023323322", "Sms message");
        eventPublisher.publishEvent(sms);

        EmailNotification email = new EmailNotification("tobi@yahoo.com", "Test Email",  "This is a test email");
        eventPublisher.publishEvent(email);

        return ResponseEntity.ok("Successful");
    }
}
