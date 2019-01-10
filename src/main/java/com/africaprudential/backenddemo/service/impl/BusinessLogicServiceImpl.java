package com.africaprudential.backenddemo.service.impl;

import com.africaprudential.backenddemo.model.Test;
import com.africaprudential.backenddemo.service.BusinessLogicService;
import com.africaprudential.backenddemo.service.PersistenceService;
import com.africaprudential.backenddemo.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessLogicServiceImpl implements BusinessLogicService {

    private final WorkflowService workflowService;
    private final PersistenceService persistenceService;

    @Override
    public Test createTest(Test test, String username) {

        workflowService.confirmWorkflow(username);
        if (test.getId() == null)
            test.setId(1L);

        return persistenceService.save(test);
    }

    @Override
    public List<Test> getAllTest(String username) {

        workflowService.confirmWorkflow(username);

        return persistenceService.getAllTests();
    }
}
