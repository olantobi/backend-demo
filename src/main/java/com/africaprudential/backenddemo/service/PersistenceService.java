package com.africaprudential.backenddemo.service;

import com.africaprudential.backenddemo.model.Test;

public interface PersistenceService {
    void save(Test test);

    Test getTest(Long id);
}
