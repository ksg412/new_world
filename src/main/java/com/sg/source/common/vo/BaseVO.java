package com.sg.source.common.vo;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
public class BaseVO implements Serializable {
    private String regId;
    private Date regDt;
    private String mdfcnId;
    private Date mdfcnDt;

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public Date getRegDt() {
        return regDt;
    }

    public void setRegDt(Date regDt) {
        this.regDt = regDt;
    }

    public String getMdfcnId() {
        return mdfcnId;
    }

    public void setMdfcnId(String mdfcnId) {
        this.mdfcnId = mdfcnId;
    }

    public Date getMdfcnDt() {
        return mdfcnDt;
    }

    public void setMdfcnDt(Date mdfcnDt) {
        this.mdfcnDt = mdfcnDt;
    }
}
