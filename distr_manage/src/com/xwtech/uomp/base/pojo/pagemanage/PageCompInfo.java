package com.xwtech.uomp.base.pojo.pagemanage;

import com.xwtech.uomp.base.pojo.OrderInfoBean;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-18
 * Time: 上午11:55
 * To change this template use File | Settings | File Templates.
 */
public class PageCompInfo extends OrderInfoBean implements Serializable {

    String compNum;
    String compName;
    String compType;
    String compUrl;
    String containerId;
    String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
