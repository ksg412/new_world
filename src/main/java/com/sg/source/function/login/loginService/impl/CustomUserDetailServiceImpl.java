package com.sg.source.function.login.loginService.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    /*@Autowired
    CustomUserDetailMapper customUserDetailMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        UserVo userVo = customUserDetailMapper.getUserbyId(id);

        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //userVo.setPw(passwordEncoder.encode("test01.html"));

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
    }*/

}
