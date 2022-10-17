package com.sg.source.common.vo;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
public class BaseVO implements Serializable {
    private String registNo;
    private Date registDt;
    private String updateNo;
    private Date updateDt;

    public String getRegistNo() {
        return registNo;
    }

    public void setRegistNo(String registNo) {
        this.registNo = registNo;
    }

    public Date getRegistDt() {
        return registDt;
    }

    public void setRegistDt(Date registDt) {
        this.registDt = registDt;
    }

    public String getUpdateNo() {
        return updateNo;
    }

    public void setUpdateNo(String updateNo) {
        this.updateNo = updateNo;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
}
