package com.xwtech.uomp.base.action.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerRequestContext {
    private HttpServletRequest request;

    private HttpServletResponse response;

    private Map<String, String> reqParam;

    public HandlerRequestContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Map<String, String> getReqParam() {
        return reqParam;
    }

    public void setReqParam(Map<String, String> reqParam) {
        this.reqParam = reqParam;
    }
}
