package com.xwtech.uomp.base.pojo.order;
public class OrderPayBean {
    private String pkid;

    private String msisdn;

    private String orderId;

    private String serialOrderId;

    private String payUrl;

    private String marketFirstPkid;

    private String marketSecondPkid;

    private String payVal;

    private String payType;

    private String payStatus;

    private String payTime;

    private String errorMsg;

	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSerialOrderId() {
		return serialOrderId;
	}

	public void setSerialOrderId(String serialOrderId) {
		this.serialOrderId = serialOrderId;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getMarketFirstPkid() {
		return marketFirstPkid;
	}

	public void setMarketFirstPkid(String marketFirstPkid) {
		this.marketFirstPkid = marketFirstPkid;
	}

	public String getMarketSecondPkid() {
		return marketSecondPkid;
	}

	public void setMarketSecondPkid(String marketSecondPkid) {
		this.marketSecondPkid = marketSecondPkid;
	}

	public String getPayVal() {
		return payVal;
	}

	public void setPayVal(String payVal) {
		this.payVal = payVal;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "OrderPayBean [errorMsg=" + errorMsg + ", marketFirstPkid="
				+ marketFirstPkid + ", marketSecondPkid=" + marketSecondPkid
				+ ", msisdn=" + msisdn + ", orderId=" + orderId
				+ ", payStatus=" + payStatus + ", payTime=" + payTime
				+ ", payType=" + payType + ", payUrl=" + payUrl + ", payVal="
				+ payVal + ", pkid=" + pkid + ", serialOrderId="
				+ serialOrderId + "]";
	}

   
}