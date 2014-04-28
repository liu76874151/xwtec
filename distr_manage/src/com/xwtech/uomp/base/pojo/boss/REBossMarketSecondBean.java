/**
 * Title: REBossMarketSecondBean.java 
 * Description: 
 * Copyright: Copyright (c) 2013
 * Company: xw
 * @ author zhanglu 
 * @ date 2013-10-29 
 * @ time 下午3:34:21
 * @ version 1.0 
 **/
package com.xwtech.uomp.base.pojo.boss;

/**
 * @author zhanglu
 *
 */
public class REBossMarketSecondBean {

	/**
	 * 二级营销方案系统编码
	 */
	private String fmarketLevel2Id;
	/**
	 * 一级营销方案系统编码
	 */
	private String fmarketLevel1Id;
	/**
	 * 二级营销方案名称
	 */
	private String fmarketLevel2Name;
	/**
	 * 品牌编码（全球通1；动感地带2；神州行3；1,2,3表示全品牌）
	 */
	private String fbrand;
	/**
	 * 开始时间
	 */
	private String fstartTime;
	/**
	 * 结束时间
	 */
	private String fendTime;
	/**
	 * 充值金额
	 */
	private String fmoneyVal;
	/**
	 * 0-失效；1-正常
	 */
	private String fuseFlag;
	/**
	 * 0-未审核；1-审核通过
	 */
	private String fischeck;
	/**
	 * 二级营销案boss编码,保留用
	 */
	private String fmarketLevel2Boss;
	/**
	 * 备注，展示说明用,包括礼品包和物品包
	 */
	private String fbz;
	/**
	 * 物品包编码(礼品包编码)
	 */
	private String fgoodsId;
	/**
	 * 物品包名称（礼品包名称）
	 */
	private String fgoodsName;
	/**
	 * 业务包编码
	 */
	private String fbusiId;
	/**
	 * 业务包名称
	 */
	private String fbusiName;
	/**
	 * @return the fmarketLevel2Id
	 */
	public String getFmarketLevel2Id() {
		return fmarketLevel2Id;
	}
	/**
	 * @param fmarketLevel2Id the fmarketLevel2Id to set
	 */
	public void setFmarketLevel2Id(String fmarketLevel2Id) {
		this.fmarketLevel2Id = fmarketLevel2Id;
	}
	/**
	 * @return the fmarketLevel1Id
	 */
	public String getFmarketLevel1Id() {
		return fmarketLevel1Id;
	}
	/**
	 * @param fmarketLevel1Id the fmarketLevel1Id to set
	 */
	public void setFmarketLevel1Id(String fmarketLevel1Id) {
		this.fmarketLevel1Id = fmarketLevel1Id;
	}
	/**
	 * @return the fmarketLevel2Name
	 */
	public String getFmarketLevel2Name() {
		return fmarketLevel2Name;
	}
	/**
	 * @param fmarketLevel2Name the fmarketLevel2Name to set
	 */
	public void setFmarketLevel2Name(String fmarketLevel2Name) {
		this.fmarketLevel2Name = fmarketLevel2Name;
	}
	/**
	 * @return the fbrand
	 */
	public String getFbrand() {
		return fbrand;
	}
	/**
	 * @param fbrand the fbrand to set
	 */
	public void setFbrand(String fbrand) {
		this.fbrand = fbrand;
	}
	/**
	 * @return the fstartTime
	 */
	public String getFstartTime() {
		return fstartTime;
	}
	/**
	 * @param fstartTime the fstartTime to set
	 */
	public void setFstartTime(String fstartTime) {
		this.fstartTime = fstartTime;
	}
	/**
	 * @return the fendTime
	 */
	public String getFendTime() {
		return fendTime;
	}
	/**
	 * @param fendTime the fendTime to set
	 */
	public void setFendTime(String fendTime) {
		this.fendTime = fendTime;
	}
	/**
	 * @return the fmoneyVal
	 */
	public String getFmoneyVal() {
		return fmoneyVal;
	}
	/**
	 * @param fmoneyVal the fmoneyVal to set
	 */
	public void setFmoneyVal(String fmoneyVal) {
		this.fmoneyVal = fmoneyVal;
	}
	/**
	 * @return the fuseFlag
	 */
	public String getFuseFlag() {
		return fuseFlag;
	}
	/**
	 * @param fuseFlag the fuseFlag to set
	 */
	public void setFuseFlag(String fuseFlag) {
		this.fuseFlag = fuseFlag;
	}
	/**
	 * @return the fischeck
	 */
	public String getFischeck() {
		return fischeck;
	}
	/**
	 * @param fischeck the fischeck to set
	 */
	public void setFischeck(String fischeck) {
		this.fischeck = fischeck;
	}
	/**
	 * @return the fmarketLevel2Boss
	 */
	public String getFmarketLevel2Boss() {
		return fmarketLevel2Boss;
	}
	/**
	 * @param fmarketLevel2Boss the fmarketLevel2Boss to set
	 */
	public void setFmarketLevel2Boss(String fmarketLevel2Boss) {
		this.fmarketLevel2Boss = fmarketLevel2Boss;
	}
	/**
	 * @return the fbz
	 */
	public String getFbz() {
		return fbz;
	}
	/**
	 * @param fbz the fbz to set
	 */
	public void setFbz(String fbz) {
		this.fbz = fbz;
	}
	/**
	 * @return the fgoodsId
	 */
	public String getFgoodsId() {
		return fgoodsId;
	}
	/**
	 * @param fgoodsId the fgoodsId to set
	 */
	public void setFgoodsId(String fgoodsId) {
		this.fgoodsId = fgoodsId;
	}
	/**
	 * @return the fgoodsName
	 */
	public String getFgoodsName() {
		return fgoodsName;
	}
	/**
	 * @param fgoodsName the fgoodsName to set
	 */
	public void setFgoodsName(String fgoodsName) {
		this.fgoodsName = fgoodsName;
	}
	/**
	 * @return the fbusiId
	 */
	public String getFbusiId() {
		return fbusiId;
	}
	/**
	 * @param fbusiId the fbusiId to set
	 */
	public void setFbusiId(String fbusiId) {
		this.fbusiId = fbusiId;
	}
	/**
	 * @return the fbusiName
	 */
	public String getFbusiName() {
		return fbusiName;
	}
	/**
	 * @param fbusiName the fbusiName to set
	 */
	public void setFbusiName(String fbusiName) {
		this.fbusiName = fbusiName;
	}
	
	
}
