/**
 * Copyright 2020 bejson.com
 */
package com.dk.searchexpress.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2020-04-10 18:24:52
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ExpressQueryBean implements Serializable {

    private String LogisticCode;//物流运单号
    private String ShipperCode;//快递公司编码
    private List<Traces> Traces;//轨迹
    private String State;//物流状态：2-在途中,3-签收,4-问题件
    private String EBusinessID;//用户ID
    private boolean Success;//成功与否：true/false

    public void setLogisticCode(String LogisticCode) {
        this.LogisticCode = LogisticCode;
    }

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setShipperCode(String ShipperCode) {
        this.ShipperCode = ShipperCode;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setTraces(List<Traces> Traces) {
        this.Traces = Traces;
    }

    public List<Traces> getTraces() {
        return Traces;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getState() {
        return State;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public boolean getSuccess() {
        return Success;
    }

}