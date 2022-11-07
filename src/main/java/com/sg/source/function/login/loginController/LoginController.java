package com.sg.source.function.login.loginController;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    //TODO 로그인 기능 개선 및 만들기

    @RequestMapping("login")
    public String login(){

        return "function/login/login";
    }

    /*@RequestMapping("getMainByUser")
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
    }*/

    public String getMainByUser2(){
        CloseableHttpClient client = HttpClients.createDefault();
        String endpointURI = "https://kakaoi-newtone-openapi.kakao.com/v1/synthesize";
        try {
            byte[] byteDataParams = "<speak>안녕하세요 반갑습니다.</speak>".getBytes("UTF-8");

            String authorization = "KakaoAK 80d135ea98cc882e308682aede3787d3";
            String contentType = "application/xml";

            HttpPost httpPost = new HttpPost(endpointURI);
            httpPost.addHeader("Authorization", authorization);
            httpPost.addHeader("Content-Type", contentType);
            CloseableHttpResponse response = client.execute(httpPost);
            int code = response.getStatusLine().getStatusCode();
            System.out.println("상태 코드 : " + code);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
