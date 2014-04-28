package com.xwtech.uomp.base.action.handler.pagemanage;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.pagemanage.PageletInfo;
import com.xwtech.uomp.base.service.pagemanage.IPageletInfoService;
import com.xwtech.uomp.base.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-19
 * Time: 上午10:54
 * To change this template use File | Settings | File Templates.
 */
@Component("H_pageletInfo")
public class PageletInfoHandler implements IHandler {

    @Autowired
    IPageletInfoService pageletInfoService;

    /**
     * 查询页面组件模板列表
     *
     * @param context
     * @return
     */
    public HandlerResult pagingQueryPageletInfoList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            Page page = pageletInfoService.pagingQueryPageletInfoList(param);
            result.setRetObj(page);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
        }

        return result;
    }

    /**
     * 根据主键查找一个页面组件模板信息
     *
     * @param context
     * @return
     */
    public HandlerResult findOnePageletInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);
        try {
            PageletInfo pageletInfo = pageletInfoService.findOnePageletInfo(param);
            result.setRetObj(pageletInfo);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
        }
        return result;
    }

    /**
     * 新增页面组件模板
     *
     * @param context
     * @return
     */
    public HandlerResult savePageletInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        PageletInfo pageletInfo = (PageletInfo) BeanUtil.getBeanFromRequest(request, PageletInfo.class);

        try {
            pageletInfoService.savePageletInfo(pageletInfo);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
        }

        return result;
    }

    /**
     * 修改页面组件模板
     *
     * @param context
     * @return
     */
    public HandlerResult updatePageletInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        PageletInfo pageletInfo = (PageletInfo) BeanUtil.getBeanFromRequest(request, PageletInfo.class);

        try {
            pageletInfoService.updatePageletInfo(pageletInfo);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_SUCCEED);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_FAILED);
        }

        return result;
    }

    /**
     * 删除页面组件模板
     *
     * @param context
     * @return
     */
    public HandlerResult deletePageletInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            pageletInfoService.deletePageletInfo(param);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
        }

        return result;
    }
}
