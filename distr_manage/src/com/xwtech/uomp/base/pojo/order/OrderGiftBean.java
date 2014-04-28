package com.xwtech.uomp.base.pojo.order;



public class OrderGiftBean {
    private String pkid;

    private String orderId;

    private String msisdn;

    private String marketFirstPkid;

    private String marketSecondPkid;

    private String giftId;

    private String giftName;

    private String giftType;

    private String giftNum;

    private String giftPrice;

    private String giftTicketId;

    private String movieId;

    private String giftSendType;

    private String giftSendStatus;

    private String giftSendTime;

    private String giftSendError;
    private String elecCouponsNum;
    private String interfaceChanelNum;

    

    public String getElecCouponsNum() {
		return elecCouponsNum;
	}

	public void setElecCouponsNum(String elecCouponsNum) {
		this.elecCouponsNum = elecCouponsNum;
	}

	public String getInterfaceChanelNum() {
		return interfaceChanelNum;
	}

	public void setInterfaceChanelNum(String interfaceChanelNum) {
		this.interfaceChanelNum = interfaceChanelNum;
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn == null ? null : msisdn.trim();
    }

    public String getMarketFirstPkid() {
        return marketFirstPkid;
    }

    public void setMarketFirstPkid(String marketFirstPkid) {
        this.marketFirstPkid = marketFirstPkid == null ? null : marketFirstPkid.trim();
    }

    public String getMarketSecondPkid() {
        return marketSecondPkid;
    }

    public void setMarketSecondPkid(String marketSecondPkid) {
        this.marketSecondPkid = marketSecondPkid == null ? null : marketSecondPkid.trim();
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId == null ? null : giftId.trim();
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName == null ? null : giftName.trim();
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType == null ? null : giftType.trim();
    }

    public String getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(String giftNum) {
        this.giftNum = giftNum == null ? null : giftNum.trim();
    }

 

    public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public String getGiftPrice() {
		return giftPrice;
	}

	public void setGiftPrice(String giftPrice) {
		this.giftPrice = giftPrice;
	}

	public String getGiftTicketId() {
        return giftTicketId;
    }

    public void setGiftTicketId(String giftTicketId) {
        this.giftTicketId = giftTicketId == null ? null : giftTicketId.trim();
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId == null ? null : movieId.trim();
    }

    public String getGiftSendType() {
        return giftSendType;
    }

    public void setGiftSendType(String giftSendType) {
        this.giftSendType = giftSendType == null ? null : giftSendType.trim();
    }

    public String getGiftSendStatus() {
        return giftSendStatus;
    }

    public void setGiftSendStatus(String giftSendStatus) {
        this.giftSendStatus = giftSendStatus == null ? null : giftSendStatus.trim();
    }

    public String getGiftSendTime() {
        return giftSendTime;
    }

    public void setGiftSendTime(String giftSendTime) {
        this.giftSendTime = giftSendTime == null ? null : giftSendTime.trim();
    }

    public String getGiftSendError() {
        return giftSendError;
    }

    public void setGiftSendError(String giftSendError) {
        this.giftSendError = giftSendError == null ? null : giftSendError.trim();
    }
}