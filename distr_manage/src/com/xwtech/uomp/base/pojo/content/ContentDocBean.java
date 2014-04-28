package com.xwtech.uomp.base.pojo.content;

import java.io.Serializable;
import java.util.List;

public class ContentDocBean implements Serializable {
	private String docId;

	private String chanId;

	private String type;

	private String title;

	private String showtitle;

	private String subhead;

	private String author;

	private String summary;

	private String url;

	private String writeTime;

	private String level;

	private String logo;

	private String versionDesc;

	private String useDesc;
	/*
	 * 资费标准
	 */
	private String feeStandard;

	private String busiName;

	private String busiNum;

	private String busiIntro;

	private String busiFee;

	private String busiOldfee;

	private String bandName;

	private String areaName;

	private String busiUrl;

	private String sortNum;

	private String state;
	private String stateLink;

	private String creater;

	private String createTime;

	private String updater;

	private String updateTime;

	private String auditor;

	private String auditTime;

	private String size;

	private String content;
	
	private List<ContentAttachmentBean> contentAttachmentBeanList;
	
	public String getStateLink() {
		return stateLink;
	}

	public void setStateLink(String stateLink) {
		this.stateLink = stateLink;
	}

	

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getChanId() {
		return chanId;
	}

	public void setChanId(String chanId) {
		this.chanId = chanId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShowtitle() {
		return showtitle;
	}

	public void setShowtitle(String showtitle) {
		this.showtitle = showtitle;
	}

	public String getSubhead() {
		return subhead;
	}

	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getVersionDesc() {
		return versionDesc;
	}

	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}

	public String getUseDesc() {
		return useDesc;
	}

	public void setUseDesc(String useDesc) {
		this.useDesc = useDesc;
	}

	public String getFeeStandard() {
		return feeStandard;
	}

	public void setFeeStandard(String feeStandard) {
		this.feeStandard = feeStandard;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getBusiNum() {
		return busiNum;
	}

	public void setBusiNum(String busiNum) {
		this.busiNum = busiNum;
	}

	public String getBusiIntro() {
		return busiIntro;
	}

	public void setBusiIntro(String busiIntro) {
		this.busiIntro = busiIntro;
	}

	public String getBusiFee() {
		return busiFee;
	}

	public void setBusiFee(String busiFee) {
		this.busiFee = busiFee;
	}

	public String getBusiOldfee() {
		return busiOldfee;
	}

	public void setBusiOldfee(String busiOldfee) {
		this.busiOldfee = busiOldfee;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getBusiUrl() {
		return busiUrl;
	}

	public void setBusiUrl(String busiUrl) {
		this.busiUrl = busiUrl;
	}

	public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ContentAttachmentBean> getContentAttachmentBeanList() {
		return contentAttachmentBeanList;
	}

	public void setContentAttachmentBeanList(
			List<ContentAttachmentBean> contentAttachmentBeanList) {
		this.contentAttachmentBeanList = contentAttachmentBeanList;
	}



}
