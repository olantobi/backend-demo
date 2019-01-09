package com.africaprudential.backenddemo.service.impl;

import com.africaprudential.backenddemo.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean confirmWorkflow(String username) {

        System.out.println("Remote service to validate workflow...");
        return true;
    }
}
