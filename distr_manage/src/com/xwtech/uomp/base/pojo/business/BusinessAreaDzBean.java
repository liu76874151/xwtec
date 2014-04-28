package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 业务地市关系表
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-18 下午04:43:39
 */
public class BusinessAreaDzBean implements Serializable{
    
    private static final long serialVersionUID = 9007247777037599975L;
    
    private String channelNum;

    private String busiNum;

    private String areaNum;

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

    public String getBusiNum() {
        return busiNum;
    }

    public void setBusiNum(String busiNum) {
        this.busiNum = busiNum;
    }

    public String getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(String areaNum) {
        this.areaNum = areaNum;
    }
}