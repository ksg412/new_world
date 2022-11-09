package com.sg.source.function.login.loginMapper;

import com.sg.source.common.annotation.Mapper;
import com.sg.source.common.vo.UserRoleVo;
import com.sg.source.common.vo.UserVo;

import java.util.List;

@Mapper
public interface CustomUserDetailMapper {

    UserVo getUserbyId(String id);

    List<UserRoleVo> getUserRoleById(String id);

}
