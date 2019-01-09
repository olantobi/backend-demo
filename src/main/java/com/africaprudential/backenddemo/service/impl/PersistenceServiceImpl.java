package com.africaprudential.backenddemo.service.impl;

import com.africaprudential.backenddemo.model.Test;
import com.africaprudential.backenddemo.service.PersistenceService;
import org.springframework.stereotype.Service;

@Service
public class PersistenceServiceImpl implements PersistenceService {

    @Override
    public void save(Test test) {

    }

    @Override
    public Test getTest(Long id) {
        return null;
    }
}
