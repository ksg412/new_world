package com.sg.source.function.login.loginMapper;

import com.sg.source.common.annotation.Mapper;
import com.sg.source.common.vo.UserVo;

@Mapper
public interface CustomUserDetailMapper {

    UserVo getUserbyId(String id);

}
