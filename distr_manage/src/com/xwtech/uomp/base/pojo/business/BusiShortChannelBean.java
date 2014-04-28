package com.xwtech.uomp.base.pojo.business;

/**
 *@ClassName:BusiShortChannelBean.java
 *@Description：
 *@author: Mars
 *@date： date：2013-12-2 time：下午04:55:59
 *@version 1.0
 */
public class BusiShortChannelBean {
/*
 * 短地址渠道标识
 */
private String  shortChannel;
/*
 * 短地址渠道名称
 */
private String  channelName;
/*
 * 备注
 */
private String  bz;
public String getShortChannel() {
	return shortChannel;
}
public void setShortChannel(String shortChannel) {
	this.shortChannel = shortChannel;
}
public String getChannelName() {
	return channelName;
}
public void setChannelName(String channelName) {
	this.channelName = channelName;
}
public String getBz() {
	return bz;
}
public void setBz(String bz) {
	this.bz = bz;
}
}
