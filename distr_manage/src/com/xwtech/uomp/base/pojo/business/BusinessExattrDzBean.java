package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 业务扩展属性关系
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-6 下午02:38:29
 */
public class BusinessExattrDzBean implements Serializable{
	
	private static final long serialVersionUID = 4720725296226540464L;

	private String attrVale;

	private String bz;

	private String busiNum;

	private String channelNum;

	private String attrKey;

	public String getAttrVale() {
		return attrVale;
	}

	public void setAttrVale(String attrVale) {
		this.attrVale = attrVale;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getBusiNum() {
		return busiNum;
	}

	public void setBusiNum(String busiNum) {
		this.busiNum = busiNum;
	}

	public String getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}

	public String getAttrKey() {
		return attrKey;
	}

	public void setAttrKey(String attrKey) {
		this.attrKey = attrKey;
	}

}