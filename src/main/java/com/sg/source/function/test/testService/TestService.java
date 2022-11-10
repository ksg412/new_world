package com.sg.source.function.test.testService;


import java.util.List;
import java.util.Map;

public interface TestService {
    List selectTbUserList(Map param) throws Exception;
    int selectTbUserCnt(Map param) throws Exception;
}
