package com.xwtech.uomp.base.action.handler.pagemanage;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.pagemanage.PageInCompList;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaBusiList;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaNonbusiList;
import com.xwtech.uomp.base.pojo.pagemanage.PageTmpInfo;
import com.xwtech.uomp.base.service.pagemanage.IPageTmpInfoService;
import com.xwtech.uomp.base.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-22
 * Time: 下午2:10
 * To change this template use File | Settings | File Templates.
 */
@Component("H_pageTmpInfo")
public class PageTmpInfoHandler implements IHandler {

    @Autowired
    IPageTmpInfoService pageTmpInfoService;

    /**
     * 分页查询页面模板信息列表
     *
     * @param context
     * @return
     */
    public HandlerResult pagingQueryPageTmpInfoList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            Page page = pageTmpInfoService.pagingQueryPageTmpInfoList(param);
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
     * 根据主键查找一个页面模板信息
     *
     * @param context
     * @return
     */
    public HandlerResult findOnePageTmpInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            PageTmpInfo pageTmpInfo = pageTmpInfoService.findOnePageTmpInfo(param);
            result.setRetObj(pageTmpInfo);
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
     * 新增页面模板
     *
     * @param context
     * @return
     */
    public HandlerResult savePageTmpInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        PageTmpInfo pageTmpInfo = (PageTmpInfo) BeanUtil.getBeanFromRequest(request, PageTmpInfo.class);
        List<PageInCompList> pageInCompList = BeanUtil.getBeanListFromRequest(request, PageInCompList.class);
        List<PageRelaBusiList> pageRelaBusiList = BeanUtil.getBeanListFromRequest(request, PageRelaBusiList.class);
        List<PageRelaNonbusiList> pageRelaNonbusiList = BeanUtil.getBeanListFromRequest(request, PageRelaNonbusiList.class);

        try {
            pageTmpInfoService.savePageTmpInfo(pageTmpInfo, pageInCompList, pageRelaBusiList, pageRelaNonbusiList);
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
     * 修改页面模板
     *
     * @param context
     * @return
     */
    public HandlerResult updatePageTmpInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        PageTmpInfo pageTmpInfo = (PageTmpInfo) BeanUtil.getBeanFromRequest(request, PageTmpInfo.class);
        List<PageInCompList> pageInCompList = BeanUtil.getBeanListFromRequest(request, PageInCompList.class);
        List<PageRelaBusiList> pageRelaBusiList = BeanUtil.getBeanListFromRequest(request, PageRelaBusiList.class);
        List<PageRelaNonbusiList> pageRelaNonbusiList = BeanUtil.getBeanListFromRequest(request, PageRelaNonbusiList.class);

        try {
            pageTmpInfoService.updatePageTmpInfo(pageTmpInfo, pageInCompList, pageRelaBusiList, pageRelaNonbusiList);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_FAILED);
        }

        return result;
    }

    /**
     * 删除页面模板
     *
     * @param context
     * @return
     */
    public HandlerResult deletePageTmpInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            pageTmpInfoService.deletePageTmpInfo(param);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
        }

        return result;
    }
}