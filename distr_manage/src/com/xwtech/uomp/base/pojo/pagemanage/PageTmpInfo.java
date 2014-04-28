package com.xwtech.uomp.base.pojo.pagemanage;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-22
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */
public class PageTmpInfo implements Serializable {

    String pageTmpNum;
    String pageTmpName;
    String tmplatePath;
    String version;
    String isRelaBusi;
    String type;
    String desc;

    public String getPageTmpNum() {
        return pageTmpNum;
    }

    public void setPageTmpNum(String pageTmpNum) {
        this.pageTmpNum = pageTmpNum;
    }

    public String getPageTmpName() {
        return pageTmpName;
    }

    public void setPageTmpName(String pageTmpName) {
        this.pageTmpName = pageTmpName;
    }

    public String getTmplatePath() {
        return tmplatePath;
    }

    public void setTmplatePath(String tmplatePath) {
        this.tmplatePath = tmplatePath;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIsRelaBusi() {
        return isRelaBusi;
    }

    public void setIsRelaBusi(String isRelaBusi) {
        this.isRelaBusi = isRelaBusi;
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

}
