package com.sg.source.function.main.service.impl;

import com.sg.source.function.main.mapper.MainMapper;
import com.sg.source.function.main.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    MainMapper mainMapper;

//    @Override
//    public String test() throws Exception {
//        return mainMapper.test();
//    }

}
