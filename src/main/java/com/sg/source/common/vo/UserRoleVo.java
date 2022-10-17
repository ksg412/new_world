package com.sg.source.common.vo;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRoleVo extends BaseVO{

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
