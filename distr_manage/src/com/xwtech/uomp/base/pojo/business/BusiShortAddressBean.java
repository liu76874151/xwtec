package com.xwtech.uomp.base.pojo.business;

import java.util.Date;

/**
 *@ClassName:BusiShortAddressBean.java
 *@Description：
 *@author: Mars
 *@date： date：2013-12-2 time：下午04:56:05
 *@version 1.0
 */
public class BusiShortAddressBean {
		/*
		 *短地址配置ID
		 */
	    private String busiNum;
	    /*
	     * 业务编码
	     */
	    private String shortId;
	    /*
	     * 渠道编码
	     */
	    private String channelNum;
	    /*
	     * 业务短地址标识
	     */
	    private String shortBusi;
	    /*
	     * 短地址渠道标识
	     */
	    private String shortChannel;
	    /*
	     * 状态位(0：可用 1：不可用)
	     */
	    private String state;
	    /*
	     * 状态更改链接
	     */
	    private String stateLink;
	    /*
	     * 开始时间
	     */
	    private Date startTime;
	    /*
	     * 结束时间
	     */
	    private Date endTime;
	    /*
	     * 创建人
	     */
	    private String creater;
	    /*
	     * 创建时间
	     */
	    private String createTime;
	    /*
	     * 修改人
	     */
	    private String updater;
	    /*
	     * 修改时间
	     */
	    private String updateTime;

		public String getShortId() {
			return shortId;
		}

		public void setShortId(String shortId) {
			this.shortId = shortId;
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

		public String getShortBusi() {
			return shortBusi;
		}

		public void setShortBusi(String shortBusi) {
			this.shortBusi = shortBusi;
		}

		public String getShortChannel() {
			return shortChannel;
		}

		public void setShortChannel(String shortChannel) {
			this.shortChannel = shortChannel;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}


		public Date getStartTime() {
			return startTime;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}

		public String getCreater() {
			return creater;
		}

		public void setCreater(String creater) {
			this.creater = creater;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getUpdater() {
			return updater;
		}

		public void setUpdater(String updater) {
			this.updater = updater;
		}

		public String getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}

		public String getStateLink() {
			return stateLink;
		}

		public void setStateLink(String stateLink) {
			this.stateLink = stateLink;
		}
}
