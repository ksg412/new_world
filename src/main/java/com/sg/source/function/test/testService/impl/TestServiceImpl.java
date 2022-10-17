package com.sg.source.function.test.testService.impl;

import com.sg.source.function.test.testService.TestService;
import com.sg.source.function.test.testMapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;


}
