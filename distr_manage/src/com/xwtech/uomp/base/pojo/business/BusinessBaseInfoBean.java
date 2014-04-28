package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;
import java.util.List;

/**
 * 业务基础信息,共性信息
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-21 上午10:59:08
 */
public class BusinessBaseInfoBean implements Serializable{
    
    private static final long serialVersionUID = 6793904142183258912L;

    private String busiNum;

    private String busiName;

    private String busiNameEn;

    private String busiNamePym1;

    private String busiNamePym2;

    private Integer state;

    private Integer suppPayWay;

    private Integer feeType;

    private String deductWay;

    private Long feeScoreQ;

    private Long feeScoreM;

    private Integer processType;

    private String busiClientUrl;

    private String jbNum;

    private Integer mj;

    private Integer jb;
    
    private String busiAdvl;

    private String busiFeature;

    private String busiPrivilege;
    
    private String prompt;
    
    private String searchWords;//add 2012-12-24
    
    private Long fee;
    
    List<BusinessEffectWayBean> businessEffectWayBeans;
    
    List<BusinessEffectWayBean> businessNEffectWayBeans;
    
    List<BusinessAreaDzBean> businessAreaDzBeans;
    
    List<BusinessBrandDzBean> businessBrandDzBeans;
    
    List<BusinessExattrDzBean> businessExattrDzBeans;
    
    List<BusinessInfoBean> businessInfoBeans;
    
    List<BusiTagDzBean> busiTagDzBeans;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSuppPayWay() {
        return suppPayWay;
    }

    public void setSuppPayWay(Integer suppPayWay) {
        this.suppPayWay = suppPayWay;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public String getDeductWay() {
        return deductWay;
    }

    public void setDeductWay(String deductWay) {
        this.deductWay = deductWay;
    }

    public Long getFeeScoreQ() {
        return feeScoreQ;
    }

    public void setFeeScoreQ(Long feeScoreQ) {
        this.feeScoreQ = feeScoreQ;
    }

    public Long getFeeScoreM() {
        return feeScoreM;
    }

    public void setFeeScoreM(Long feeScoreM) {
        this.feeScoreM = feeScoreM;
    }

    public Integer getProcessType() {
        return processType;
    }

    public void setProcessType(Integer processType) {
        this.processType = processType;
    }

    public String getBusiClientUrl() {
        return busiClientUrl;
    }

    public void setBusiClientUrl(String busiClientUrl) {
        this.busiClientUrl = busiClientUrl;
    }

    public String getJbNum() {
        return jbNum;
    }

    public void setJbNum(String jbNum) {
        this.jbNum = jbNum;
    }

    public Integer getMj() {
        return mj;
    }

    public void setMj(Integer mj) {
        this.mj = mj;
    }

    public Integer getJb() {
        return jb;
    }

    public void setJb(Integer jb) {
        this.jb = jb;
    }

    public String getBusiAdvl() {
        return busiAdvl;
    }

    public void setBusiAdvl(String busiAdvl) {
        this.busiAdvl = busiAdvl;
    }

    public String getBusiFeature() {
        return busiFeature;
    }

    public void setBusiFeature(String busiFeature) {
        this.busiFeature = busiFeature;
    }

    public String getBusiPrivilege() {
        return busiPrivilege;
    }

    public void setBusiPrivilege(String busiPrivilege) {
        this.busiPrivilege = busiPrivilege;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public List<BusinessEffectWayBean> getBusinessEffectWayBeans() {
        return businessEffectWayBeans;
    }

    public void setBusinessEffectWayBeans(List<BusinessEffectWayBean> businessEffectWayBeans) {
        this.businessEffectWayBeans = businessEffectWayBeans;
    }

    public List<BusinessAreaDzBean> getBusinessAreaDzBeans() {
        return businessAreaDzBeans;
    }

    public void setBusinessAreaDzBeans(List<BusinessAreaDzBean> businessAreaDzBeans) {
        this.businessAreaDzBeans = businessAreaDzBeans;
    }

    public List<BusinessBrandDzBean> getBusinessBrandDzBeans() {
        return businessBrandDzBeans;
    }

    public void setBusinessBrandDzBeans(List<BusinessBrandDzBean> businessBrandDzBeans) {
        this.businessBrandDzBeans = businessBrandDzBeans;
    }

    public List<BusinessEffectWayBean> getBusinessNEffectWayBeans() {
        return businessNEffectWayBeans;
    }

    public void setBusinessNEffectWayBeans(List<BusinessEffectWayBean> businessNEffectWayBeans) {
        this.businessNEffectWayBeans = businessNEffectWayBeans;
    }

    public List<BusinessExattrDzBean> getBusinessExattrDzBeans() {
        return businessExattrDzBeans;
    }

    public void setBusinessExattrDzBeans(List<BusinessExattrDzBean> businessExattrDzBeans) {
        this.businessExattrDzBeans = businessExattrDzBeans;
    }

    public List<BusinessInfoBean> getBusinessInfoBeans() {
        return businessInfoBeans;
    }

    public void setBusinessInfoBeans(List<BusinessInfoBean> businessInfoBeans) {
        this.businessInfoBeans = businessInfoBeans;
    }
    
    public String getSearchWords() {
        return searchWords;
    }

    public void setSearchWords(String searchWords) {
        this.searchWords = searchWords;
    }

    public List<BusiTagDzBean> getBusiTagDzBeans() {
        return busiTagDzBeans;
    }

    public void setBusiTagDzBeans(List<BusiTagDzBean> busiTagDzBeans) {
        this.busiTagDzBeans = busiTagDzBeans;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }
}