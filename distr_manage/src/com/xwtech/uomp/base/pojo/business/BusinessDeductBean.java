package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 扣费方式 This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-11-6 下午04:40:53
 */
public class BusinessDeductBean implements Serializable{
	
	private static final long serialVersionUID = 9026641580280530472L;

	private String deductNum;

	private String deductWay;

	private String bz;
	
	private String modify;

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

	public String getDeductNum() {
		return deductNum;
	}

	public void setDeductNum(String deductNum) {
		this.deductNum = deductNum;
	}

	public String getDeductWay() {
		return deductWay;
	}

	public void setDeductWay(String deductWay) {
		this.deductWay = deductWay;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}