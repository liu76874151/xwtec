package com.xwtech.uomp.base.pojo.pagemanage;

import com.xwtech.uomp.base.pojo.OrderInfoBean;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-19
 * Time: 上午10:00
 * To change this template use File | Settings | File Templates.
 */
public class PageletInfo extends OrderInfoBean implements Serializable {

    String pageletNum;
    String pageletName;
    String version;
    String vmTemplate;
    String step;
    String desc;

    public String getPageletNum() {
        return pageletNum;
    }

    public void setPageletNum(String pageletNum) {
        this.pageletNum = pageletNum;
    }

    public String getPageletName() {
        return pageletName;
    }

    public void setPageletName(String pageletName) {
        this.pageletName = pageletName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVmTemplate() {
        return vmTemplate;
    }

    public void setVmTemplate(String vmTemplate) {
        this.vmTemplate = vmTemplate;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
