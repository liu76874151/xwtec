package com.xwtech.uomp.base.pojo.pagemanage;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-11-5
 * Time: 下午3:43
 * To change this template use File | Settings | File Templates.
 */
public class PageRelaNonbusiList implements Serializable {

    String pageTmpNum;
    String version;
    String nonbusiNum;
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

    public String getNonbusiNum() {
        return nonbusiNum;
    }

    public void setNonbusiNum(String nonbusiNum) {
        this.nonbusiNum = nonbusiNum;
    }

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen;
    }
}
