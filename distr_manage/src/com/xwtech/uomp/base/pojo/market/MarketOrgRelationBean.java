package com.xwtech.uomp.base.pojo.market;

import java.io.Serializable;

import com.xwtech.uomp.base.pojo.org.OrgInfoBean;

/**
 * 二级营销案与营业厅关联 This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-10-23 上午11:14:08
 */
public class MarketOrgRelationBean implements Serializable {

	private static final long serialVersionUID = -6254197975317778031L;

	private String orgId;

	private String marketSecondPkid;

	private Long orgCode;

	private OrgInfoBean orgInfoBean;

	public OrgInfoBean getOrgInfoBean() {
		return orgInfoBean;
	}

	public void setOrgInfoBean(OrgInfoBean orgInfoBean) {
		this.orgInfoBean = orgInfoBean;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getMarketSecondPkid() {
		return marketSecondPkid;
	}

	public void setMarketSecondPkid(String marketSecondPkid) {
		this.marketSecondPkid = marketSecondPkid;
	}

	public Long getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(Long orgCode) {
		this.orgCode = orgCode;
	}
}