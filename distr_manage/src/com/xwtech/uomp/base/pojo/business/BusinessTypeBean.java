package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 业务分类 This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-11-7 上午11:20:51
 */
public class BusinessTypeBean implements Serializable {
    
    private static final long serialVersionUID = -6098181033530636201L;

    private String busiTypeNum;

    private String busiTypeName;

    private String busiTypeIcon;

    private String busiTypeIcon2;

    private String busiTypeIcon3;

    private String busiTypeIcon4;

    private String busiTypeIcon5;

    private String busiTypeIcon6;

    private String busiTypeIcon7;

    private String busiTypeIcon8;

    private Integer loadType;

    private Integer showXh;

    private String bz;

    private String apdAttrib1;

    private String apdAttrib2;

    private String busiTypeDesc;

    private String busiTypeYx;

    private String parentNum;// 父级级别编码
    private String jbNum;
    private Integer jb;
    private Integer mj;
    
    private String channelNum;

    public String getParentNum() {
	return parentNum;
    }

    public void setParentNum(String parentNum) {
	this.parentNum = parentNum;
    }

    public String getBusiTypeNum() {
	return busiTypeNum;
    }

    public void setBusiTypeNum(String busiTypeNum) {
	this.busiTypeNum = busiTypeNum;
    }

    public String getBusiTypeName() {
	return busiTypeName;
    }

    public void setBusiTypeName(String busiTypeName) {
	this.busiTypeName = busiTypeName;
    }

    public String getBusiTypeIcon() {
	return busiTypeIcon;
    }

    public void setBusiTypeIcon(String busiTypeIcon) {
	this.busiTypeIcon = busiTypeIcon;
    }

    public String getBusiTypeIcon2() {
	return busiTypeIcon2;
    }

    public void setBusiTypeIcon2(String busiTypeIcon2) {
	this.busiTypeIcon2 = busiTypeIcon2;
    }

    public String getBusiTypeIcon3() {
	return busiTypeIcon3;
    }

    public void setBusiTypeIcon3(String busiTypeIcon3) {
	this.busiTypeIcon3 = busiTypeIcon3;
    }

    public String getBusiTypeIcon4() {
	return busiTypeIcon4;
    }

    public void setBusiTypeIcon4(String busiTypeIcon4) {
	this.busiTypeIcon4 = busiTypeIcon4;
    }

    public String getBusiTypeIcon5() {
	return busiTypeIcon5;
    }

    public void setBusiTypeIcon5(String busiTypeIcon5) {
	this.busiTypeIcon5 = busiTypeIcon5;
    }

    public String getBusiTypeIcon6() {
	return busiTypeIcon6;
    }

    public void setBusiTypeIcon6(String busiTypeIcon6) {
	this.busiTypeIcon6 = busiTypeIcon6;
    }

    public String getBusiTypeIcon7() {
	return busiTypeIcon7;
    }

    public void setBusiTypeIcon7(String busiTypeIcon7) {
	this.busiTypeIcon7 = busiTypeIcon7;
    }

    public String getBusiTypeIcon8() {
	return busiTypeIcon8;
    }

    public void setBusiTypeIcon8(String busiTypeIcon8) {
	this.busiTypeIcon8 = busiTypeIcon8;
    }

    public Integer getLoadType() {
	return loadType;
    }

    public void setLoadType(Integer loadType) {
	this.loadType = loadType;
    }

    public Integer getShowXh() {
	return showXh;
    }

    public void setShowXh(Integer showXh) {
	this.showXh = showXh;
    }

    public String getBz() {
	return bz;
    }

    public void setBz(String bz) {
	this.bz = bz;
    }

    public String getApdAttrib1() {
	return apdAttrib1;
    }

    public void setApdAttrib1(String apdAttrib1) {
	this.apdAttrib1 = apdAttrib1;
    }

    public String getApdAttrib2() {
	return apdAttrib2;
    }

    public void setApdAttrib2(String apdAttrib2) {
	this.apdAttrib2 = apdAttrib2;
    }

    public String getBusiTypeDesc() {
	return busiTypeDesc;
    }

    public void setBusiTypeDesc(String busiTypeDesc) {
	this.busiTypeDesc = busiTypeDesc;
    }

    public String getBusiTypeYx() {
	return busiTypeYx;
    }

    public void setBusiTypeYx(String busiTypeYx) {
	this.busiTypeYx = busiTypeYx;
    }

    public String getJbNum() {
	return jbNum;
    }

    public void setJbNum(String jbNum) {
	this.jbNum = jbNum;
    }

    public Integer getJb() {
	return jb;
    }

    public void setJb(Integer jb) {
	this.jb = jb;
    }

    public Integer getMj() {
	return mj;
    }

    public void setMj(Integer mj) {
	this.mj = mj;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }
}