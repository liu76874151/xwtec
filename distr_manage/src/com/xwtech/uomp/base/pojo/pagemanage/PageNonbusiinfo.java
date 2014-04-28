package com.xwtech.uomp.base.pojo.pagemanage;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-11-6
 * Time: 下午4:35
 * To change this template use File | Settings | File Templates.
 */
public class PageNonbusiinfo implements Serializable {

    String nonbusiNum;
    String nonbusiName;
    String bz;
    String type;

    public String getNonbusiNum() {
        return nonbusiNum;
    }

    public void setNonbusiNum(String nonbusiNum) {
        this.nonbusiNum = nonbusiNum;
    }

    public String getNonbusiName() {
        return nonbusiName;
    }

    public void setNonbusiName(String nonbusiName) {
        this.nonbusiName = nonbusiName;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
