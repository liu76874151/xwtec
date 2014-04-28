package com.xwtech.uomp.base.pojo.pagemanage;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-23
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
public class PageInCompList implements Serializable {
    String pageTmpNum;
    String version;
    String compNum;
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

    public String getCompNum() {
        return compNum;
    }

    public void setCompNum(String compNum) {
        this.compNum = compNum;
    }

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen;
    }
}
