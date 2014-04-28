package com.xwtech.uomp.base.pojo;

import java.util.Map;

public class PageDynamicRequestInfo {
    private String dynamicDataNodeName;
    private String dynamicURI;
    private Map<String, String> dynamicParameter;

    public String getDynamicDataNodeName() {
        return dynamicDataNodeName;
    }

    public void setDynamicDataNodeName(String dynamicDataNodeName) {
        this.dynamicDataNodeName = dynamicDataNodeName;
    }

    public String getDynamicURI() {
        return dynamicURI;
    }

    public void setDynamicURI(String dynamicURI) {
        this.dynamicURI = dynamicURI;
    }

    public Map<String, String> getDynamicParameter() {
        return dynamicParameter;
    }

    public void setDynamicParameter(Map<String, String> dynamicParameter) {
        this.dynamicParameter = dynamicParameter;
    }
}
