package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-11-18 下午01:43:38
 */
public class BusinessSortDzBean implements Serializable {
    private static final long serialVersionUID = 7832468165594773180L;

    private Integer xh;

    private String channelNum;
    
    private String busiTypeNum;

    private String busiNum;

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

    public String getBusiTypeNum() {
        return busiTypeNum;
    }

    public void setBusiTypeNum(String busiTypeNum) {
        this.busiTypeNum = busiTypeNum;
    }

    public String getBusiNum() {
        return busiNum;
    }

    public void setBusiNum(String busiNum) {
        this.busiNum = busiNum;
    }
}