package com.sg.source.common.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class UserVo extends BaseVO {
    
    private String id;
    private String pw;
    private List<UserRoleVo>  userRoleVoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public List<UserRoleVo> getUserRoleVoList() {
        return userRoleVoList;
    }

    public void setUserRoleVoList(List<UserRoleVo> userRoleVoList) {
        this.userRoleVoList = userRoleVoList;
    }
}
