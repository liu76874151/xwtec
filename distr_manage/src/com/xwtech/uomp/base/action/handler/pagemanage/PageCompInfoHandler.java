package com.xwtech.uomp.base.action.handler.pagemanage;

import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.pagemanage.PageCompInfo;
import com.xwtech.uomp.base.service.pagemanage.IPageCompInfoService;
import com.xwtech.uomp.base.util.BeanUtil;
import com.xwtech.uomp.base.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-18
 * Time: 上午11:52
 * To change this template use File | Settings | File Templates.
 */
@Component("H_pageCompInfo")
public class PageCompInfoHandler implements IHandler {

    @Autowired
    IPageCompInfoService pageCompInfoService;

    /**
     * 查询页面组件信息列表
     *
     * @param context
     * @return
     */
    public HandlerResult pagingQueryPageCompInfoList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            Page page = pageCompInfoService.pagingQueryPageCompInfoList(param);
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
     * 根据主键查找一个页面组件信息
     *
     * @param context
     * @return
     */
    public HandlerResult findOnePageCompInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        String pkid = RequestUtil.getString(request, "pkid");
        try {
            PageCompInfo pageCompInfo = pageCompInfoService.findOnePageCompInfo(pkid);
            result.setRetObj(pageCompInfo);
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
     * 新增页面组件
     *
     * @param context
     * @return
     */
    public HandlerResult savePageCompInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        PageCompInfo pageCompInfo = (PageCompInfo) BeanUtil.getBeanFromRequest(request, PageCompInfo.class);

        try {
            pageCompInfoService.savePageCompInfo(pageCompInfo);
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
     * 修改页面组件
     *
     * @param context
     * @return
     */
    public HandlerResult updatePageCompInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        PageCompInfo pageCompInfo = (PageCompInfo) BeanUtil.getBeanFromRequest(request, PageCompInfo.class);

        try {
            pageCompInfoService.updatePageCompInfo(pageCompInfo);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_FAILED);
        }

        return result;
    }

    /**
     * 删除页面组件
     *
     * @param context
     * @return
     */
    public HandlerResult deletePageCompInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        String id = RequestUtil.getString(request, "pkid");

        try {
            pageCompInfoService.deletePageCompInfo(id);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
        }

        return result;
    }
}
