package com.sg.source.function.test.testService.impl;

import com.sg.source.function.test.testService.TestService;
import com.sg.source.function.test.testMapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    @Override
    public List selectTbUserList(Map param) throws Exception {
        return testMapper.selectTbUserList(param);
    }

    @Override
    public int selectTbUserCnt(Map param) throws Exception {
        return testMapper.selectTbUserCnt(param);
    }
}
