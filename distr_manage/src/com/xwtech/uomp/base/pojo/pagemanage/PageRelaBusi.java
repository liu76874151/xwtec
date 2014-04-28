package com.xwtech.uomp.base.pojo.pagemanage;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-24
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
public class PageRelaBusi implements Serializable {

    String busiNum;
    String busiName;
    String pageTmpNum;
    String version;
    String isopen;

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
