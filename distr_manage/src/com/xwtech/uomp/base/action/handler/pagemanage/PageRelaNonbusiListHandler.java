package com.xwtech.uomp.base.action.handler.pagemanage;

import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaNonbusi;
import com.xwtech.uomp.base.service.pagemanage.IPageRelaNonbusiListService;
import com.xwtech.uomp.base.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-11-5
 * Time: 下午3:07
 * To change this template use File | Settings | File Templates.
 */
@Component("H_pageRelaNonbusiList")
public class PageRelaNonbusiListHandler implements IHandler {

    @Autowired
    IPageRelaNonbusiListService pageRelaNonbusiListService;

    /**
     * 查询页面关联非业务信息列表，查询出所有的非业务列表，并包含已关联的
     *
     * @param context
     * @return
     */
    public HandlerResult queryPageRelaNonbusiList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            List<PageRelaNonbusi> list = pageRelaNonbusiListService.queryNonbusiUsedList(param);
            result.setRetObj(JSONArray.toJSONString(list));
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
        }

        return result;
    }

    /**
     * 查询页面关联非业务信息列表，查询出关联的非业务列表
     *
     * @param context
     * @return
     */
    public HandlerResult queryPageRelaNonbusiUsedList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            List<PageRelaNonbusi> list = pageRelaNonbusiListService.queryPageRelaNonbusiList(param);
            result.setRetObj(JSONArray.toJSONString(list));
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
        }

        return result;
    }
}