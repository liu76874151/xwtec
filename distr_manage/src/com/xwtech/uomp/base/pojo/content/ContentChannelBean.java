package com.xwtech.uomp.base.pojo.content;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-10-14
 * Time: 下午5:03
 * To change this template use File | Settings | File Templates.
 */
public class ContentChannelBean implements Serializable {
    Long chanId;
    String channelNum;
    String chanNum;
    String chanName;
    String showName;
    Long parentId;
    String parentName;
    String logo;
    String type;
    String desc;
    String url;
    String depository;
    String sortNum;
    String state;
    String overviewTmp;
    String detailTmp;
    String creater;
    Timestamp createTime;
    String updater;
    Timestamp updateTime;
    String overviewTmp2;
    String detailTmp2;

    public Long getChanId() {
        return chanId;
    }

    public void setChanId(Long chanId) {
        this.chanId = chanId;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

    public String getChanNum() {
        return chanNum;
    }

    public void setChanNum(String chanNum) {
        this.chanNum = chanNum;
    }

    public String getChanName() {
        return chanName;
    }

    public void setChanName(String chanName) {
        this.chanName = chanName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDepository() {
        return depository;
    }

    public void setDepository(String depository) {
        this.depository = depository;
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

    public String getOverviewTmp() {
        return overviewTmp;
    }

    public void setOverviewTmp(String overviewTmp) {
        this.overviewTmp = overviewTmp;
    }

    public String getDetailTmp() {
        return detailTmp;
    }

    public void setDetailTmp(String detailTmp) {
        this.detailTmp = detailTmp;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getOverviewTmp2() {
        return overviewTmp2;
    }

    public void setOverviewTmp2(String overviewTmp2) {
        this.overviewTmp2 = overviewTmp2;
    }

    public String getDetailTmp2() {
        return detailTmp2;
    }

    public void setDetailTmp2(String detailTmp2) {
        this.detailTmp2 = detailTmp2;
    }
}
