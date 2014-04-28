package com.xwtech.uomp.base.pojo.boss;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * boss 二级营销案
 * @author zhangel
 *
 */
public class BossMarketSecondBean implements Serializable {

	private static final long serialVersionUID = -3979391983011184820L;

	private String marketFirstCode;//BOSS一级营销方案标识
	private String marketSecondName;//BOSS二级营销方案名称
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String brandId;//品牌标识
	private BigDecimal moneyVal;//充值金额
	private String tchannal;//渠道  1,网厅（村组，校园，网点属于网厅），2，掌厅，3短厅
	private String marketSecondCode;//BOSS二级营销方案标识
	private String cityId;//地市标识
	private String boosBizStr;
	private BossBindBizBean boosBizSecond;
	private String boosBizSecondListStr;
	private List<BossBindBizBean> boosBizList;
	private String boosGiftInfoStr;
	private BossGiftInfoBean boosGiftInfo;
	private String boosGiftInfoListStr;
	private List<BossGiftInfoBean> boosGiftInfoList;
	

	public String getBoosBizStr() {
		return boosBizStr;
	}

	public void setBoosBizStr(String boosBizStr) {
		this.boosBizStr = boosBizStr;
	}

	public BossBindBizBean getBoosBizSecond() {
		return boosBizSecond;
	}

	public void setBoosBizSecond(BossBindBizBean boosBizSecond) {
		this.boosBizSecond = boosBizSecond;
	}

	public String getBoosBizSecondListStr() {
		return boosBizSecondListStr;
	}

	public void setBoosBizSecondListStr(String boosBizSecondListStr) {
		this.boosBizSecondListStr = boosBizSecondListStr;
	}

	public String getBoosGiftInfoStr() {
		return boosGiftInfoStr;
	}

	public void setBoosGiftInfoStr(String boosGiftInfoStr) {
		this.boosGiftInfoStr = boosGiftInfoStr;
	}

	public BossGiftInfoBean getBoosGiftInfo() {
		return boosGiftInfo;
	}

	public void setBoosGiftInfo(BossGiftInfoBean boosGiftInfo) {
		this.boosGiftInfo = boosGiftInfo;
	}

	public String getBoosGiftInfoListStr() {
		return boosGiftInfoListStr;
	}

	public void setBoosGiftInfoListStr(String boosGiftInfoListStr) {
		this.boosGiftInfoListStr = boosGiftInfoListStr;
	}

	public List<BossBindBizBean> getBoosBizList() {
		return boosBizList;
	}

	public void setBoosBizList(List<BossBindBizBean> boosBizList) {
		this.boosBizList = boosBizList;
	}

	public List<BossGiftInfoBean> getBoosGiftInfoList() {
		return boosGiftInfoList;
	}

	public void setBoosGiftInfoList(List<BossGiftInfoBean> boosGiftInfoList) {
		this.boosGiftInfoList = boosGiftInfoList;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}


	
	public String getTchannal() {
		return tchannal;
	}

	public void setTchannal(String tchannal) {
		this.tchannal = tchannal;
	}

	public String getMarketSecondCode() {
		return marketSecondCode;
	}

	public void setMarketSecondCode(String marketSecondCode) {
		this.marketSecondCode = marketSecondCode;
	}

	public String getMarketFirstCode() {
		return marketFirstCode;
	}

	public void setMarketFirstCode(String marketFirstCode) {
		this.marketFirstCode = marketFirstCode == null ? null : marketFirstCode
				.trim();
	}

	public String getMarketSecondName() {
		return marketSecondName;
	}

	public void setMarketSecondName(String marketSecondName) {
		this.marketSecondName = marketSecondName == null ? null
				: marketSecondName.trim();
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

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId == null ? null : brandId.trim();
	}

	public BigDecimal getMoneyVal() {
		return moneyVal;
	}

	public void setMoneyVal(BigDecimal moneyVal) {
		this.moneyVal = moneyVal;
	}


}