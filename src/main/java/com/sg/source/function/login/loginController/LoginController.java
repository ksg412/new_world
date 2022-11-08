package com.sg.source.function.login.loginController;

import com.sg.source.common.util.CommonUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class LoginController {

    //TODO 로그인 기능 개선 및 만들기

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model){

        return "function/login/login";
    }

    @RequestMapping("getMainByUser")
    public String getMainByUser(){
        Set<SimpleGrantedAuthority> auths = (Set<SimpleGrantedAuthority>) CommonUtil.getUserDetails().getAuthorities();

        String rootAuth = "";
        for(SimpleGrantedAuthority auth : auths){
            String roleName = auth.getAuthority();
            if(roleName.contains("KESCO")){
                rootAuth = roleName;
            }else if(roleName.contains("KEPCO")){
                rootAuth = roleName;
            }else if(roleName.contains("CLIENT")){
                rootAuth = roleName;
            }
        }

        if(rootAuth.contains("KESCO")){
            return "user/kesco/main/main";
        }else if(rootAuth.contains("KEPCO")){
            return "user/kepco/main/main";
        }else{
            return "user/client/main/main";
        }
    }
}
