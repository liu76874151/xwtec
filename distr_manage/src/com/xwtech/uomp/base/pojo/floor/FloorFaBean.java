package com.xwtech.uomp.base.pojo.floor;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-12
 * Time: 下午4:49
 * To change this template use File | Settings | File Templates.
 */
public class FloorFaBean implements Serializable {

    Long floorId;
    Long planId;
    String floorNum;
    String floorName;
    int floorSeq;
    String floorImage;
    String floorUrl;
    String tempNum;
    String floorBz;
    String floorComp;
    String channelNum;
    List<FloorBlockFaBean>floorBlockFaBeanlist;

    /**
	 * @return the channelNum
	 */
	public String getChannelNum() {
		return channelNum;
	}

	/**
	 * @param channelNum the channelNum to set
	 */
	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}

	/**
	 * @return the floorBlockFaBeanlist
	 */
	public List<FloorBlockFaBean> getFloorBlockFaBeanlist() {
		return floorBlockFaBeanlist;
	}

	/**
	 * @param floorBlockFaBeanlist the floorBlockFaBeanlist to set
	 */
	public void setFloorBlockFaBeanlist(List<FloorBlockFaBean> floorBlockFaBeanlist) {
		this.floorBlockFaBeanlist = floorBlockFaBeanlist;
	}

	public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public int getFloorSeq() {
        return floorSeq;
    }

    public void setFloorSeq(int floorSeq) {
        this.floorSeq = floorSeq;
    }

    public String getFloorImage() {
        return floorImage;
    }

    public void setFloorImage(String floorImage) {
        this.floorImage = floorImage;
    }

    public String getFloorUrl() {
        return floorUrl;
    }

    public void setFloorUrl(String floorUrl) {
        this.floorUrl = floorUrl;
    }

    public String getTempNum() {
        return tempNum;
    }

    public void setTempNum(String tempNum) {
        this.tempNum = tempNum;
    }

    public String getFloorBz() {
        return floorBz;
    }

    public void setFloorBz(String floorBz) {
        this.floorBz = floorBz;
    }

    public String getFloorComp() {
        return floorComp;
    }

    public void setFloorComp(String floorComp) {
        this.floorComp = floorComp;
    }
}
