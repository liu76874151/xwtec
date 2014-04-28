package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.xwtech.uomp.base.pojo.OrderInfoBean;

/**
 * 业务信息
 * This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-11-5 下午02:02:48
 */
public class BusinessInfoBean extends OrderInfoBean implements Serializable {

	private static final long serialVersionUID = 1818768618261872358L;
	private String busiNum;

	private String busiName;

	private String busiNameEn;

	private String busiNamePym1;

	private String busiNamePym2;

	private String busiIcon;

	private String busiMenuIcon;

	private String busiDescIcon;

	private String flash;

	private Integer processType;

	private Long fee;

	private Long feeScoreM;

	private Long feeScoreQ;

	private Integer feeType;

	private String countUnit;

	private Integer suppPayWay;

	private String deductWay;

	private Integer state;

	private String jbNum;

	private int jb;

	private int mj;

	private String bz;

	private String busiPicUrl;

	private String busiClientUrl;

	private String channelNum;

	private String busiTrancnum;

	private Date startTime;

	private Date endTime;

	private String intro;

	private String busiNameHtml;

	private String busiDesc;

	private String busiAdvl;

	private String busiExpl;

	private String prompt;

	private String busiFeature;

	private String busiNestFt;

	private String busiNestSd;

	private String busiPrivilege;
	
	private List<BusinessTypeDzBean> businessTypeDzBeans;
	
	private List<RelationBusinessInfoBean> relationBusinessInfoBeans;

	public String getBusiNum() {
		return busiNum;
	}

	public void setBusiNum(String busiNum) {
		this.busiNum = busiNum;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getBusiNameEn() {
		return busiNameEn;
	}

	public void setBusiNameEn(String busiNameEn) {
		this.busiNameEn = busiNameEn;
	}

	public String getBusiNamePym1() {
		return busiNamePym1;
	}

	public void setBusiNamePym1(String busiNamePym1) {
		this.busiNamePym1 = busiNamePym1;
	}

	public String getBusiNamePym2() {
		return busiNamePym2;
	}

	public void setBusiNamePym2(String busiNamePym2) {
		this.busiNamePym2 = busiNamePym2;
	}

	public String getBusiIcon() {
		return busiIcon;
	}

	public void setBusiIcon(String busiIcon) {
		this.busiIcon = busiIcon;
	}

	public String getBusiMenuIcon() {
		return busiMenuIcon;
	}

	public void setBusiMenuIcon(String busiMenuIcon) {
		this.busiMenuIcon = busiMenuIcon;
	}

	public String getBusiDescIcon() {
		return busiDescIcon;
	}

	public void setBusiDescIcon(String busiDescIcon) {
		this.busiDescIcon = busiDescIcon;
	}

	public String getFlash() {
		return flash;
	}

	public void setFlash(String flash) {
		this.flash = flash;
	}

	public Integer getProcessType() {
		return processType;
	}

	public void setProcessType(Integer processType) {
		this.processType = processType;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public Long getFeeScoreM() {
		return feeScoreM;
	}

	public void setFeeScoreM(Long feeScoreM) {
		this.feeScoreM = feeScoreM;
	}

	public Long getFeeScoreQ() {
		return feeScoreQ;
	}

	public void setFeeScoreQ(Long feeScoreQ) {
		this.feeScoreQ = feeScoreQ;
	}

	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public String getCountUnit() {
		return countUnit;
	}

	public void setCountUnit(String countUnit) {
		this.countUnit = countUnit;
	}

	public Integer getSuppPayWay() {
		return suppPayWay;
	}

	public void setSuppPayWay(Integer suppPayWay) {
		this.suppPayWay = suppPayWay;
	}

	public String getDeductWay() {
		return deductWay;
	}

	public void setDeductWay(String deductWay) {
		this.deductWay = deductWay;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getJbNum() {
		return jbNum;
	}

	public void setJbNum(String jbNum) {
		this.jbNum = jbNum;
	}

	public int getJb() {
		return jb;
	}

	public void setJb(int jb) {
		this.jb = jb;
	}

	public int getMj() {
		return mj;
	}

	public void setMj(int mj) {
		this.mj = mj;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getBusiPicUrl() {
		return busiPicUrl;
	}

	public void setBusiPicUrl(String busiPicUrl) {
		this.busiPicUrl = busiPicUrl;
	}

	public String getBusiClientUrl() {
		return busiClientUrl;
	}

	public void setBusiClientUrl(String busiClientUrl) {
		this.busiClientUrl = busiClientUrl;
	}

	public String getChannelNum() {
		return channelNum;
	}

	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}

	public String getBusiTrancnum() {
		return busiTrancnum;
	}

	public void setBusiTrancnum(String busiTrancnum) {
		this.busiTrancnum = busiTrancnum;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getBusiNameHtml() {
		return busiNameHtml;
	}

	public void setBusiNameHtml(String busiNameHtml) {
		this.busiNameHtml = busiNameHtml;
	}

	public String getBusiDesc() {
		return busiDesc;
	}

	public void setBusiDesc(String busiDesc) {
		this.busiDesc = busiDesc;
	}

	public String getBusiAdvl() {
		return busiAdvl;
	}

	public void setBusiAdvl(String busiAdvl) {
		this.busiAdvl = busiAdvl;
	}

	public String getBusiExpl() {
		return busiExpl;
	}

	public void setBusiExpl(String busiExpl) {
		this.busiExpl = busiExpl;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getBusiFeature() {
		return busiFeature;
	}

	public void setBusiFeature(String busiFeature) {
		this.busiFeature = busiFeature;
	}

	public String getBusiNestFt() {
		return busiNestFt;
	}

	public void setBusiNestFt(String busiNestFt) {
		this.busiNestFt = busiNestFt;
	}

	public String getBusiNestSd() {
		return busiNestSd;
	}

	public void setBusiNestSd(String busiNestSd) {
		this.busiNestSd = busiNestSd;
	}

	public String getBusiPrivilege() {
		return busiPrivilege;
	}

	public void setBusiPrivilege(String busiPrivilege) {
		this.busiPrivilege = busiPrivilege;
	}

	public List<BusinessTypeDzBean> getBusinessTypeDzBeans() {
	    return businessTypeDzBeans;
	}

	public void setBusinessTypeDzBeans(List<BusinessTypeDzBean> businessTypeDzBeans) {
	    this.businessTypeDzBeans = businessTypeDzBeans;
	}

	public List<RelationBusinessInfoBean> getRelationBusinessInfoBeans() {
	    return relationBusinessInfoBeans;
	}

	public void setRelationBusinessInfoBeans(List<RelationBusinessInfoBean> relationBusinessInfoBeans) {
	    this.relationBusinessInfoBeans = relationBusinessInfoBeans;
	}
	
}
