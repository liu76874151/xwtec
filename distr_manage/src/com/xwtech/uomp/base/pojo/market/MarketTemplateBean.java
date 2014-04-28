package com.xwtech.uomp.base.pojo.market;

import java.io.Serializable;

/**
 * 协议模板
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-10-23 下午05:10:17
 */
public class MarketTemplateBean implements Serializable {
	
	private static final long serialVersionUID = 3832037764110141682L;

	private Long templateId;

    private String templateName;

    private String type;

    private String contentDir;

    private Short city;

    private Long brand;

    private String category;

    private String state;

    private String channalData;

    private String content;
    
    private String areaName;

    /**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContentDir() {
        return contentDir;
    }

    public void setContentDir(String contentDir) {
        this.contentDir = contentDir == null ? null : contentDir.trim();
    }

    public Short getCity() {
        return city;
    }

    public void setCity(Short city) {
        this.city = city;
    }

    public Long getBrand() {
        return brand;
    }

    public void setBrand(Long brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getChannalData() {
        return channalData;
    }

    public void setChannalData(String channalData) {
        this.channalData = channalData == null ? null : channalData.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}