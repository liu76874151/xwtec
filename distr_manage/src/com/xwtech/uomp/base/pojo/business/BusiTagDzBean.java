package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 业务标签与业务关联
 * 业务共性信息
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-24 下午02:48:52
 */
public class BusiTagDzBean implements Serializable{
    
    private static final long serialVersionUID = 1890776514404753967L;

    private Long tagId;

    private String busiNum;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getBusiNum() {
        return busiNum;
    }

    public void setBusiNum(String busiNum) {
        this.busiNum = busiNum;
    }
}