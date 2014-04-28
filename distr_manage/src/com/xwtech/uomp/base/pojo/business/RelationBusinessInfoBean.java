package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;
import java.util.List;

/**
 * 关联业务关联信息
 * This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-12-3 下午04:59:40
 */
public class RelationBusinessInfoBean implements Serializable{
    
    private static final long serialVersionUID = -6283169034566028394L;

    private Integer priority;
    
    private String busiNum;

    private String channelNum;

    private String relativeBusi;
    
    List<RelationBusinessInfoAreaDzBean> relationBusinessInfoAreaDzBeans;
    
    List<RelationBusinessInfoBrandDzBean> relationBusinessInfoBrandDzBeans;

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getBusiNum() {
        return busiNum;
    }

    public void setBusiNum(String busiNum) {
        this.busiNum = busiNum;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

    public String getRelativeBusi() {
        return relativeBusi;
    }

    public void setRelativeBusi(String relativeBusi) {
        this.relativeBusi = relativeBusi;
    }

    public List<RelationBusinessInfoAreaDzBean> getRelationBusinessInfoAreaDzBeans() {
        return relationBusinessInfoAreaDzBeans;
    }

    public void setRelationBusinessInfoAreaDzBeans(
    	List<RelationBusinessInfoAreaDzBean> relationBusinessInfoAreaDzBeans) {
        this.relationBusinessInfoAreaDzBeans = relationBusinessInfoAreaDzBeans;
    }

    public List<RelationBusinessInfoBrandDzBean> getRelationBusinessInfoBrandDzBeans() {
        return relationBusinessInfoBrandDzBeans;
    }

    public void setRelationBusinessInfoBrandDzBeans(
    	List<RelationBusinessInfoBrandDzBean> relationBusinessInfoBrandDzBeans) {
        this.relationBusinessInfoBrandDzBeans = relationBusinessInfoBrandDzBeans;
    }

}