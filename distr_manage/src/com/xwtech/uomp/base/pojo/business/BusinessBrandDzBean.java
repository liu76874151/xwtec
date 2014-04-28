package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 业务品牌关系
 *  This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-11-18 下午04:54:57
 */
public class BusinessBrandDzBean implements Serializable {

    private static final long serialVersionUID = 4628288498500637345L;

    private String busiNum;
    private String brandNum;
    private String channelNum;

    public String getBusiNum() {
	return busiNum;
    }

    public void setBusiNum(String busiNum) {
	this.busiNum = busiNum;
    }

    public String getBrandNum() {
	return brandNum;
    }

    public void setBrandNum(String brandNum) {
	this.brandNum = brandNum;
    }

    public String getChannelNum() {
	return channelNum;
    }

    public void setChannelNum(String channelNum) {
	this.channelNum = channelNum;
    }

}