package com.sg.source.function.login.loginService.impl;

import com.sg.source.common.vo.SecurityVo;
import com.sg.source.common.vo.UserRoleVo;
import com.sg.source.common.vo.UserVo;
import com.sg.source.function.login.loginMapper.CustomUserDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleList;
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
        List<UserRoleVo> roleList = customUserDetailMapper.getUserRoleById(id);
        if(roleList.size() == 0){
            UserRoleVo userRoleVo = new UserRoleVo();
            userRoleVo.setAuthCode("ROLL_USER");
            List userRoleVoList = new ArrayList<UserRoleVo>();
            userRoleVoList.add(userRoleVo);
            userVo.setUserRoleVoList(userRoleVoList);
        }else{
            userVo.setUserRoleVoList(roleList);
        }

        /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userVo.setPw(passwordEncoder.encode("admin"));*/

        return new SecurityVo(userVo);

    }

}
