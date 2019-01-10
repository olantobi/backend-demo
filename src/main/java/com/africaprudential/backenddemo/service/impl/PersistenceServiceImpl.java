package com.africaprudential.backenddemo.service.impl;

import com.africaprudential.backenddemo.model.Test;
import com.africaprudential.backenddemo.service.PersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PersistenceServiceImpl implements PersistenceService {

    @Override
    public Test save(Test test) {
        log.info("Saving tests...");

        return test;
    }

    @Override
    public Test getTest(Long id) {
        return null;
    }

    @Override
    public List<Test> getAllTests() {
        System.out.println("Getting all tests data...");
        List<Test> tests = new ArrayList<>();
        tests.add(new Test(1l, "Test 1"));
        tests.add(new Test(2l, "Test 2"));

        return tests;
    }
}
