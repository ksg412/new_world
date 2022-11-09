package com.sg.source.common.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class UserVo extends BaseVO {
    private String userId;
    private String password;
    private String mblTelno;
    private String email;
    private String useYn;
    private List<UserRoleVo>  userRoleVoList;

}
