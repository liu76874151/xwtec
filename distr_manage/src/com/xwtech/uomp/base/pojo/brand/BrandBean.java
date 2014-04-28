package com.xwtech.uomp.base.pojo.brand;

import java.io.Serializable;

/**
 * 品牌
 * @author xwtec
 */
public class BrandBean implements Serializable{

	private static final long serialVersionUID = 6016483378130733573L;
	
	private String brandNum;
	private String brandName;
	private String jbNum;
	private String memo;
	private String bossCode;
	private Integer mj;
	private Integer jb;

	public String getBrandNum() {
		return brandNum;
	}

	public void setBrandNum(String brandNum) {
		this.brandNum = brandNum;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getJbNum() {
		return jbNum;
	}

	public void setJbNum(String jbNum) {
		this.jbNum = jbNum;
	}


	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getBossCode() {
		return bossCode;
	}

	public void setBossCode(String bossCode) {
		this.bossCode = bossCode;
	}

	public Integer getMj() {
	    return mj;
	}

	public void setMj(Integer mj) {
	    this.mj = mj;
	}

	public Integer getJb() {
	    return jb;
	}

	public void setJb(Integer jb) {
	    this.jb = jb;
	}
}
