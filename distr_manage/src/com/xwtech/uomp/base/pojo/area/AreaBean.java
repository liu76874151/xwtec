package com.xwtech.uomp.base.pojo.area;

import java.io.Serializable;

import com.xwtech.uomp.base.pojo.OrderInfoBean;

public class AreaBean extends OrderInfoBean implements Serializable {
	/*
	 * 地市编码
	 */
    private String areaNum;
    /*
     * 地市名称
     */
    private String areaName;
    /*
     * 级别编码
     */
    private String areaJbNum;
    /*
     * 级别
     */
    private String areaJb;
    /*
     * 是否末级（0-否； 1-是）
     */
    private String areaMj;
    /*
     * 备注
     */
	private String areaBz;
	/*
	 * 对应的BOSS编码
	 */
    private String areaBossCode;

    private String areaBossOrgid;

	public String getAreaNum() {
		return areaNum;
	}

	public void setAreaNum(String areaNum) {
		this.areaNum = areaNum;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaJbNum() {
		return areaJbNum;
	}

	public void setAreaJbNum(String areaJbNum) {
		this.areaJbNum = areaJbNum;
	}

	public String getAreaJb() {
		return areaJb;
	}

	public void setAreaJb(String areaJb) {
		this.areaJb = areaJb;
	}

	public String getAreaMj() {
		return areaMj;
	}

	public void setAreaMj(String areaMj) {
		this.areaMj = areaMj;
	}

	public String getAreaBz() {
		return areaBz;
	}

	public void setAreaBz(String areaBz) {
		this.areaBz = areaBz;
	}

	public String getAreaBossCode() {
		return areaBossCode;
	}

	public void setAreaBossCode(String areaBossCode) {
		this.areaBossCode = areaBossCode;
	}

	public String getAreaBossOrgid() {
		return areaBossOrgid;
	}

	public void setAreaBossOrgid(String areaBossOrgid) {
		this.areaBossOrgid = areaBossOrgid;
	}

   




}