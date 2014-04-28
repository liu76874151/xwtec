package com.xwtech.uomp.base.pojo.memcache;

import java.io.Serializable;

public class CacheTypeManageBean implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2724128996990277068L;

    /**
     * 缓存类型名称
     */
    private String typeName;

    /**
     * 缓存类型编码
     */
    private String typeNum;

    /**
     * 缓存描述
     */
    private String desc;

    public CacheTypeManageBean(String typeName, String typeNum, String desc) {
        this.typeName = typeName;
        this.typeNum = typeNum;
        this.desc = desc;
    }


    public CacheTypeManageBean() {
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(String typeNum) {
        this.typeNum = typeNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
