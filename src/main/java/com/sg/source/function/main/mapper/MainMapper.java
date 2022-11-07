package com.sg.source.function.main.mapper;

import com.sg.source.common.annotation.Mapper;

import java.util.Map;

@Mapper
public interface MainMapper {
    //public String test() throws Exception;
    Map getTbMapList() throws Exception;
}
