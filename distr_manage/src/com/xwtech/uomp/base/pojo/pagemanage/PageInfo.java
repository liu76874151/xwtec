package com.xwtech.uomp.base.pojo.pagemanage;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-22
 * Time: 上午9:31
 * To change this template use File | Settings | File Templates.
 */
public class PageInfo implements Serializable {

    String pageNum;
    String pageName;
    String version;
    String pageContent;
    String confirmComp;
    String resultComp;
    String type;
    String desc;
    Timestamp updateTime;

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public String getConfirmComp() {
        return confirmComp;
    }

    public void setConfirmComp(String confirmComp) {
        this.confirmComp = confirmComp;
    }

    public String getResultComp() {
        return resultComp;
    }

    public void setResultComp(String resultComp) {
        this.resultComp = resultComp;
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


    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
