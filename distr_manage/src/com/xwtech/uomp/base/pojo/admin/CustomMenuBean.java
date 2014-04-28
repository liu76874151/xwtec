package com.xwtech.uomp.base.pojo.admin;

import java.io.Serializable;

public class CustomMenuBean implements Serializable {

    /**
     * 序列ID
     */
    private static final long serialVersionUID = 717464608572605704L;

    /**
     * 子系统编码
     */
    private String subSysNum;

    /**
     * 子系统编码
     */
    private String subSysName;

    /**
     * 登陆名称
     */
    private String loginUserCode;


    /**
     * 模块分类编码
     */
    //private String mouldSortNum;

    /**
     * 模块分类名称
     */
    private String moudleSortName;

    /**
     * 对应功能编码
     */
    private String funcId;

    /**
     * 对应功能名称
     */
    private String funcName;

    /**
     * 对应功能url
     */
    private String funcUri;

    /**
     * 级别编码
     */
    private String jbNum;

    /**
     * *
     * 级别
     */
    private int jb;

    /**
     * 是否是末级
     */
    private int mj;

    /**
     * 权限树父ID *
     */
    private String pid;

    /**
     * 备注
     */
    private String bz;

    public String getSubSysNum() {
        return subSysNum;
    }

    public void setSubSysNum(String subSysNum) {
        this.subSysNum = subSysNum;
    }

    public String getMoudleSortName() {
        return moudleSortName;
    }

    public void setMoudleSortName(String moudleSortName) {
        this.moudleSortName = moudleSortName;
    }

    public String getLoginUserCode() {
        return loginUserCode;
    }

    public void setLoginUserCode(String loginUserCode) {
        this.loginUserCode = loginUserCode;
    }

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public String getJbNum() {
        return jbNum;
    }

    public void setJbNum(String jbNum) {
        this.jbNum = jbNum;
    }

    public int getJb() {
        return jb;
    }

    public void setJb(int jb) {
        this.jb = jb;
    }

    public int getMj() {
        return mj;
    }

    public void setMj(int mj) {
        this.mj = mj;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSubSysName() {
        return subSysName;
    }

    public void setSubSysName(String subSysName) {
        this.subSysName = subSysName;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncUri() {
        return funcUri;
    }

    public void setFuncUri(String funcUri) {
        this.funcUri = funcUri;
    }

    public CustomMenuBean() {
    }

    public CustomMenuBean(String subSysNum, String loginUserCode, String moudleSortName, String funcId, String jbNum, int jb, int mj, String bz) {
        this.subSysNum = subSysNum;
        this.loginUserCode = loginUserCode;
        this.moudleSortName = moudleSortName;
        this.funcId = funcId;
        this.jbNum = jbNum;
        this.jb = jb;
        this.mj = mj;
        this.bz = bz;
    }

    public CustomMenuBean(String subSysNum, String loginUserCode, String funcId, String jbNum, int jb) {
        this.subSysNum = subSysNum;
        this.loginUserCode = loginUserCode;
        this.funcId = funcId;
        this.jbNum = jbNum;
        this.jb = jb;
    }


}
