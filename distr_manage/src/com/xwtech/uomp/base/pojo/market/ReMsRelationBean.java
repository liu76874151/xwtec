package com.xwtech.uomp.base.pojo.market;

public class ReMsRelationBean {
    private String marketFirstPkid;

    private String groupId;

    private String state;

    private String optTime;

	public String getMarketFirstPkid() {
		return marketFirstPkid;
	}

	public void setMarketFirstPkid(String marketFirstPkid) {
		this.marketFirstPkid = marketFirstPkid;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOptTime() {
		return optTime;
	}

	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}

	@Override
	public String toString() {
		return "ReMsRelationBean [groupId=" + groupId + ", marketFirstPkid="
				+ marketFirstPkid + ", optTime=" + optTime + ", state=" + state
				+ "]";
	}

	
    
}
