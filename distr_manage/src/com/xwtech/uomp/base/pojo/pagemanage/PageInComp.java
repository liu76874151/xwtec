package com.xwtech.uomp.base.pojo.pagemanage;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-23
 * Time: 上午10:35
 * To change this template use File | Settings | File Templates.
 */
public class PageInComp implements Serializable {

    String compNum;
    String compName;
    String compType;
    String compUrl;
    String containerId;
    String pageTmpNum;
    String version;
    String isopen;

    public String getCompNum() {
        return compNum;
    }

    public void setCompNum(String compNum) {
        this.compNum = compNum;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    public String getCompUrl() {
        return compUrl;
    }

    public void setCompUrl(String compUrl) {
        this.compUrl = compUrl;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getPageTmpNum() {
        return pageTmpNum;
    }

    public void setPageTmpNum(String pageTmpNum) {
        this.pageTmpNum = pageTmpNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen;
    }


}

