package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-4 下午04:23:43
 */
public class BusinessEffectWayBean implements Serializable {

	private static final long serialVersionUID = 187964846674737384L;

	private String busiNum;
	private String busiOprtType;
	private String effectWay;

	public String getBusiNum() {
		return busiNum;
	}

	public void setBusiNum(String busiNum) {
		this.busiNum = busiNum;
	}

	public String getBusiOprtType() {
		return busiOprtType;
	}

	public void setBusiOprtType(String busiOprtType) {
		this.busiOprtType = busiOprtType;
	}

	public String getEffectWay() {
		return effectWay;
	}

	public void setEffectWay(String effectWay) {
		this.effectWay = effectWay;
	}

}
