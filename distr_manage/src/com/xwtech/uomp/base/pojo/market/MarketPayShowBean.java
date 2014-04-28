package com.xwtech.uomp.base.pojo.market;

import java.io.Serializable;

/**
 * 二级营销案支付方式
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 下午03:04:25
 */
public class MarketPayShowBean implements Serializable{
	
	private static final long serialVersionUID = 2675505765658551021L;

	private Short payId;

    private String marketSecondPkid;

    private Short state;

    public Short getPayId() {
        return payId;
    }

    public void setPayId(Short payId) {
        this.payId = payId;
    }

    public String getMarketSecondPkid() {
        return marketSecondPkid;
    }

    public void setMarketSecondPkid(String marketSecondPkid) {
        this.marketSecondPkid = marketSecondPkid;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }
}