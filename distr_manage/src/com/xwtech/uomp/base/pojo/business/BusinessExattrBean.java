package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 业务扩展属性
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-5 下午03:06:03
 */
public class BusinessExattrBean implements Serializable{

	private static final long serialVersionUID = 5802581617994780219L;

	private String attrKey;

	private String attrName;

	private Integer attrType;

	private String attrDesc;
	
	private String modify;

	public String getAttrKey() {
		return attrKey;
	}

	public void setAttrKey(String attrKey) {
		this.attrKey = attrKey;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Integer getAttrType() {
		return attrType;
	}

	public void setAttrType(Integer attrType) {
		this.attrType = attrType;
	}

	public String getAttrDesc() {
		return attrDesc;
	}

	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

}