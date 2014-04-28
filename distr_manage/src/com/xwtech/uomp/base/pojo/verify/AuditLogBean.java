package com.xwtech.uomp.base.pojo.verify;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 审核流程记录
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-13 下午05:28:25
 */
public class AuditLogBean implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long pkid;

    private String bisuId;

    private String channelNum;

    private String auditState;

    private String operType;

    private String operLevel;

    private Timestamp operTime;

    private String operUser;

    private String description;

    public Long getPkid() {
        return pkid;
    }

    public void setPkid(Long pkid) {
        this.pkid = pkid;
    }

    public String getBisuId() {
        return bisuId;
    }

    public void setBisuId(String bisuId) {
        this.bisuId = bisuId;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getOperLevel() {
        return operLevel;
    }

    public void setOperLevel(String operLevel) {
        this.operLevel = operLevel;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}