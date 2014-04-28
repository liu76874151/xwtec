package com.xwtech.uomp.base.pojo.brand;

/**
 * 
 * This class is used for ...   
 * @author unique  
 * @version   
 *       1.0, 2014-1-23 上午10:07:56
 */
public final class BrandSortBean {
    
    private Long brandId;

    private Long parentId;

    private Short brandLevel;

    private String brandName;

    private Long bossCode;

    private String brandDescribe;

    private String brandState;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Short getBrandLevel() {
        return brandLevel;
    }

    public void setBrandLevel(Short brandLevel) {
        this.brandLevel = brandLevel;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public Long getBossCode() {
        return bossCode;
    }

    public void setBossCode(Long bossCode) {
        this.bossCode = bossCode;
    }

    public String getBrandDescribe() {
        return brandDescribe;
    }

    public void setBrandDescribe(String brandDescribe) {
        this.brandDescribe = brandDescribe == null ? null : brandDescribe.trim();
    }

    public String getBrandState() {
        return brandState;
    }

    public void setBrandState(String brandState) {
        this.brandState = brandState == null ? null : brandState.trim();
    }
}