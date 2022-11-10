package com.sg.source.function.test.testMapper;

import com.sg.source.common.annotation.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {
    List selectTbUserList(Map param) throws Exception;
    int selectTbUserCnt(Map param) throws Exception;
}
