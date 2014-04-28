package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 业务标签
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-23 下午03:00:54
 */
public class BusiTagBean implements Serializable{
    
    private static final long serialVersionUID = -6092991667338165410L;

    private Long tagId;

    private String tagName;

    private String tagEname;

    private String tagDesc;

    private String tagState;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagEname() {
        return tagEname;
    }

    public void setTagEname(String tagEname) {
        this.tagEname = tagEname;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }

    public String getTagState() {
        return tagState;
    }

    public void setTagState(String tagState) {
        this.tagState = tagState;
    }
    
}