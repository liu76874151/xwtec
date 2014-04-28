package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 
 * This class is used for ...
 * 
 * @author zhangel
 * @version 1.0, 2013-12-2 下午02:17:34
 */
public class BusinessTypeDzBean implements Serializable{
    
    private static final long serialVersionUID = 6374157608599466093L;

    private String busiTypeNum;

    private String busiNum;

    private Integer xh;

    private String channelNum;

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
}