package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-3 下午05:51:00
 */
public class RelationBusinessInfoAreaDzBean implements Serializable{
    
    private static final long serialVersionUID = -2236556553021250654L;

    private String busiNum;

    private String channelNum;

    private String relativeBusi;

    private String areaNum;

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

    public String getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(String areaNum) {
        this.areaNum = areaNum;
    }
    
}