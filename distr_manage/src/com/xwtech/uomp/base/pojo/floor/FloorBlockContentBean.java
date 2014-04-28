/**
 * Title: FloorBlockContent.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-11-14 
 * @ time 下午5:28:32
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.pojo.floor;

/**
 * @author zhanglu
 *
 */
public class FloorBlockContentBean {

	/**
	 * 内容ID
	 */
	String contentId;
	/**
	 * 区块ID
	 */
	String blockId;
	/**
	 * 类别（1：文字 2：链接）
	 */
	String contentType;
	/**
	 * 标题
	 */
	String contentTitle;
	/**
	 * 说明
	 */
	String contentDesc;
	/**
	 * 图片
	 */
	String contentImg;
	/**
	 * 链接
	 */
	String contentUrl;
	/**
	 * 业务编码
	 */
	String busiNum;
	/**
	 * 排序号
	 */
	String sortNum;
	/**
	 * 状态(0：可用 1：删除)
	 */
	String state;
	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}
	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the contentTitle
	 */
	public String getContentTitle() {
		return contentTitle;
	}
	/**
	 * @param contentTitle the contentTitle to set
	 */
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	/**
	 * @return the contentDesc
	 */
	public String getContentDesc() {
		return contentDesc;
	}
	/**
	 * @param contentDesc the contentDesc to set
	 */
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	/**
	 * @return the contentImg
	 */
	public String getContentImg() {
		return contentImg;
	}
	/**
	 * @param contentImg the contentImg to set
	 */
	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}
	/**
	 * @return the contentUrl
	 */
	public String getContentUrl() {
		return contentUrl;
	}
	/**
	 * @param contentUrl the contentUrl to set
	 */
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	/**
	 * @return the busiNum
	 */
	public String getBusiNum() {
		return busiNum;
	}
	/**
	 * @param busiNum the busiNum to set
	 */
	public void setBusiNum(String busiNum) {
		this.busiNum = busiNum;
	}
	/**
	 * @return the sortNum
	 */
	public String getSortNum() {
		return sortNum;
	}
	/**
	 * @param sortNum the sortNum to set
	 */
	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
}
