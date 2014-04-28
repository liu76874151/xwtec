package com.xwtech.uomp.base.pojo;

public class OrderInfoBean {
    /**
     * 级别编码
     */
    public String jbNum;

    public String parentId;

    /**
     * 级别
     */
    public int jb;

    /**
     * 是否末级（0-否； 1-是）
     */
    public int mj;

    public int getJb() {
        return jb;
    }

    public void setJb(int jb) {
        this.jb = jb;
    }

    public String getJbNum() {
        return jbNum;
    }

    public void setJbNum(String jbNum) {
        this.jbNum = jbNum;
    }

    public int getMj() {
        return mj;
    }

    public void setMj(int mj) {
        this.mj = mj;
    }

}
