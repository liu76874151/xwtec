package com.xwtech.uomp.base.pojo.order;



public class RechargeMktBillBean {
    private String cityId;

    private String msisdn;

    private String type;

    private String amount;

    private String bankState;

    private String busiState;

    private String busiTime;

    private String orderId;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn == null ? null : msisdn.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getBankState() {
        return bankState;
    }

    public void setBankState(String bankState) {
        this.bankState = bankState == null ? null : bankState.trim();
    }

    public String getBusiState() {
        return busiState;
    }

    public void setBusiState(String busiState) {
        this.busiState = busiState == null ? null : busiState.trim();
    }

    public String getBusiTime() {
        return busiTime;
    }

    public void setBusiTime(String busiTime) {
        this.busiTime = busiTime == null ? null : busiTime.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }
}