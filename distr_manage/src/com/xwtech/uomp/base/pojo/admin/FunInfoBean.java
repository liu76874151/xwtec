package com.xwtech.uomp.base.pojo.admin;

import java.io.Serializable;

import com.xwtech.uomp.base.pojo.OrderInfoBean;

public class FunInfoBean extends OrderInfoBean implements Serializable {
    private static final long serialVersionUID = 717464608572605704L;

    public FunInfoBean() {
    }

    public FunInfoBean(String jbNum, String parentId, String funcName, int jb) {
        this.jbNum = jbNum;
        this.parentId = parentId;
        this.funcName = funcName;
        this.jb = jb;
    }

    public FunInfoBean(String funId, String funcURI, String funcName,
                       String bz, String subSysName, String jbNum, String funcType) {
        this.funcId = funId;
        this.funcURI = funcURI;
        this.funcName = funcName;
        this.bz = bz;
        this.subSysName = subSysName;
        this.jbNum = jbNum;
        this.funcType = funcType;
    }

    public FunInfoBean(String subSysName, String funcId, String funcName,
                       String funcType, String funcURI, String bz, String jbNum, int mj, int jb) {

        this.subSysName = subSysName;
        this.funcId = funcId;
        this.funcName = funcName;
        this.funcType = funcType;
        this.funcURI = funcURI;
        this.bz = bz;
        this.jbNum = jbNum;
        this.jb = jb;
        this.mj = mj;
    }

    public static final FunInfoBean node0 = new FunInfoBean("0", "-1",
            "", 0);

    private String subSysName;

    /**
     * 模块ID
     */
    private String funcId;

    /**
     * 模块名称
     */
    private String funcName;

    /**
     * 模块类型
     */
    private String funcType;

    /**
     * URI
     */
    private String funcURI;

    /**
     * 备注
     */
    private String bz;

    public String getSubSysName() {
        return subSysName;
    }

    public void setSubSysName(String subSysName) {
        this.subSysName = subSysName;
    }

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcid) {
        this.funcId = funcid;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncType() {
        return funcType;
    }

    public void setFuncType(String funcType) {
        this.funcType = funcType;
    }

    public String getFuncURI() {
        return funcURI;
    }

    public void setFuncURI(String funcURI) {
        this.funcURI = funcURI;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
