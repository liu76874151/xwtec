package com.xwtech.uomp.base.pojo.business;

import java.io.Serializable;

/**
 * 关联业务的关联品牌
 * This class is used for ...   
 * @author zhangel  
 * @version   
 *       1.0, 2013-12-3 下午05:27:12
 */
public class RelationBusinessInfoBrandDzBean implements Serializable{
    
    private static final long serialVersionUID = -4431210827529593132L;

    private String busiNum;

    private String channelNum;

    private String relativeBusi;

    private String brandNum;

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

    public String getBrandNum() {
        return brandNum;
    }

    public void setBrandNum(String brandNum) {
        this.brandNum = brandNum;
    }
}