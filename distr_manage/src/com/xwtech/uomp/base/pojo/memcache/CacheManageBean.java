package com.xwtech.uomp.base.pojo.memcache;

import java.io.Serializable;

public class CacheManageBean implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5288604979216943283L;

    /**
     * 缓存key
     */
    private String memKey;

    /**
     * 缓存存放对应的cache名称
     */
    private String cachedName;

    /**
     * DAO类的名称
     */
    private String daoName;

    /**
     * DAO类中的方法名称
     */
    private String daoMethod;

    /**
     * key值是否需要参数 1：是 2：否
     */
    private String isNeedParam;

    /**
     * 缓存中存放的时间
     */
    private int expireInSeconds;

    /**
     * 备注
     */
    private String bz;

    /**
     * 类型。1：DB数据 2：普通数据
     */
    private String keyType;

    public CacheManageBean() {
    }

    public CacheManageBean(String memKey, String cachedName, String daoName,
                           String daoMethod, String isNeedParam, int expireInSeconds,
                           String bz, String keyType) {
        this.memKey = memKey;
        this.cachedName = cachedName;
        this.daoName = daoName;
        this.daoMethod = daoMethod;
        this.isNeedParam = isNeedParam;
        this.expireInSeconds = expireInSeconds;
        this.bz = bz;
        this.keyType = keyType;
    }

    public String getMemKey() {
        return memKey;
    }

    public void setMemKey(String memKey) {
        this.memKey = memKey;
    }

    public String getCachedName() {
        return cachedName;
    }

    public void setCachedName(String cachedName) {
        this.cachedName = cachedName;
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String daoName) {
        this.daoName = daoName;
    }

    public String getDaoMethod() {
        return daoMethod;
    }

    public void setDaoMethod(String daoMethod) {
        this.daoMethod = daoMethod;
    }

    public String getIsNeedParam() {
        return isNeedParam;
    }

    public void setIsNeedParam(String isNeedParam) {
        this.isNeedParam = isNeedParam;
    }

    public int getExpireInSeconds() {
        return expireInSeconds;
    }

    public void setExpireInSeconds(int expireInSeconds) {
        this.expireInSeconds = expireInSeconds;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }
}
