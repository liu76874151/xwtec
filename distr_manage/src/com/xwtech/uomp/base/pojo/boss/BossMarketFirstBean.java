package com.xwtech.uomp.base.pojo.boss;

import java.io.Serializable;
import java.util.List;

/**
 * boss 一级营销案
 * @author zhangel
 *
 */
public class BossMarketFirstBean implements Serializable{
	
	private static final long serialVersionUID = -4860339192065480675L;
	private String marketFirstName;
	private String startTime;
	private String endTime;
	private String type;
	private String tchannal;
	private String marketFirstCode;
    private String cityId;
    private List<BossMarketSecondBean> BossMarketSecondBean;
    
	public List<BossMarketSecondBean> getBossMarketSecondBean() {
		return BossMarketSecondBean;
	}

	public void setBossMarketSecondBean(
			List<BossMarketSecondBean> bossMarketSecondBean) {
		BossMarketSecondBean = bossMarketSecondBean;
	}

	public String getTchannal() {
		return tchannal;
	}

	public void setTchannal(String tchannal) {
		this.tchannal = tchannal;
	}

	public String getMarketFirstCode() {
		return marketFirstCode;
	}

	public void setMarketFirstCode(String marketFirstCode) {
		this.marketFirstCode = marketFirstCode;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getMarketFirstName() {
		return marketFirstName;
	}

	public void setMarketFirstName(String marketFirstName) {
		this.marketFirstName = marketFirstName == null ? null : marketFirstName
				.trim();
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime == null ? null : startTime.trim();
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime == null ? null : endTime.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}
}