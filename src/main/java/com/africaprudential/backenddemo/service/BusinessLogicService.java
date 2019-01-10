package com.africaprudential.backenddemo.service;

import com.africaprudential.backenddemo.model.Test;

import java.util.List;

public interface BusinessLogicService {

    List<Test> getAllTest(String username);

    Test createTest(Test test, String username);
}
