/**
 * Copyright 2020 bejson.com
 */
package com.dk.searchexpress.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Auto-generated: 2020-04-10 18:24:52
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Traces implements Serializable {

    private String AcceptStation;//描述
    private Date AcceptTime;//时间
    private String Remark;//备注,可以为null

    public void setAcceptStation(String AcceptStation) {
        this.AcceptStation = AcceptStation;
    }

    public String getAcceptStation() {
        return AcceptStation;
    }

    public void setAcceptTime(Date AcceptTime) {
        this.AcceptTime = AcceptTime;
    }

    public Date getAcceptTime() {
        return AcceptTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}