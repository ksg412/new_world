package com.sg.source.function.login.loginService.impl;

import com.sg.source.common.vo.SecurityVo;
import com.sg.source.common.vo.UserRoleVo;
import com.sg.source.common.vo.UserVo;
import com.sg.source.function.login.loginMapper.CustomUserDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private CustomUserDetailMapper customUserDetailMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        UserVo userVo = customUserDetailMapper.getUserbyId(id);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userVo.setPw(passwordEncoder.encode("test01.html"));

        //TODO 이후 권한 처리 작업 필요
        if(null == userVo.getUserRoleVoList() || userVo.getUserRoleVoList().size() == 0){
            UserRoleVo userRoleVo = new UserRoleVo();
            userRoleVo.setRoleName("CLIENT");
            List userRoleVoList = new ArrayList<UserRoleVo>();
            userRoleVoList.add(userRoleVo);
            userVo.setUserRoleVoList(userRoleVoList);
        }



        return Optional.ofNullable(userVo)
                .filter(m -> m!= null)
                .map(m -> new SecurityVo(m)).get();

    }

}
