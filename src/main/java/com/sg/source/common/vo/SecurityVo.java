package com.sg.source.common.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

public class SecurityVo extends User {

    private static final long serialVersionUID = 1L;

    public SecurityVo(UserVo userVo) {
        super(userVo.getUserId(), userVo.getPassword(), makeGrantedAuthority(userVo.getUserRoleVoList()));
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<UserRoleVo> roles){
        if(null != roles){
            List<GrantedAuthority> list = new ArrayList<>();
            roles.forEach(role -> list.add(new SimpleGrantedAuthority(role.getAuthCode())));
            return list;
        }else{
            return null;
        }
    }
}
