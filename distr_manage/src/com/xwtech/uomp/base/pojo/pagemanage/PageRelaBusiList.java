package com.xwtech.uomp.base.pojo.pagemanage;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-24
 * Time: 下午4:35
 * To change this template use File | Settings | File Templates.
 */
public class PageRelaBusiList implements Serializable {
    String pageTmpNum;
    String version;
    String busiNum;
    String isopen;

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

    public String getBusiNum() {
        return busiNum;
    }

    public void setBusiNum(String busiNum) {
        this.busiNum = busiNum;
    }

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen;
    }

}
