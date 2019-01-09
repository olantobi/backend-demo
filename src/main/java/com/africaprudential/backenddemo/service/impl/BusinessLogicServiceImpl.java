package com.africaprudential.backenddemo.service.impl;

import com.africaprudential.backenddemo.model.Test;
import com.africaprudential.backenddemo.service.BusinessLogicService;
import org.springframework.stereotype.Service;

@Service
public class BusinessLogicServiceImpl implements BusinessLogicService {

    @Override
    public Test testService(Test test) {
        return test;
    }
}
