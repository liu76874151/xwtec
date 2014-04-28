package com.xwtech.uomp.base.pojo.market;

import java.io.Serializable;

import com.xwtech.uomp.base.pojo.org.TargetGroupInfoBean;

/**
 * 营销案目标组织
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 上午09:35:05
 */
public class MarketGroupRelationBean implements Serializable{
	
	private static final long serialVersionUID = 9093084772908891147L;

	private String relationId;

    private String marketSecondPkid;

    private Long groupId;

    private String state;

    private String optTime;
    private String channel;
    
    
    public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	private TargetGroupInfoBean targetGroupInfoBean;

    public TargetGroupInfoBean getTargetGroupInfoBean() {
		return targetGroupInfoBean;
	}

	public void setTargetGroupInfoBean(TargetGroupInfoBean targetGroupInfoBean) {
		this.targetGroupInfoBean = targetGroupInfoBean;
	}

	public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getMarketSecondPkid() {
        return marketSecondPkid;
    }

    public void setMarketSecondPkid(String marketSecondPkid) {
        this.marketSecondPkid = marketSecondPkid;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime == null ? null : optTime.trim();
    }
}