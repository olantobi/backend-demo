package com.africaprudential.backenddemo.service;

import com.africaprudential.backenddemo.model.Test;

import java.util.List;

public interface PersistenceService {
    Test save(Test test);

    Test getTest(Long id);
    List<Test> getAllTests();
}
