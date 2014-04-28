package com.xwtech.uomp.base.action.handler.pagemanage;

import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.pagemanage.PageRelaBusi;
import com.xwtech.uomp.base.service.pagemanage.IPageRelaBusiListService;
import com.xwtech.uomp.base.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-7-24
 * Time: 下午4:49
 * To change this template use File | Settings | File Templates.
 */
@Component("H_pageRelaBusiList")
public class PageRelaBusiListHandler implements IHandler {

    @Autowired
    IPageRelaBusiListService pageRelaBusiListService;

    /**
     * 查询页面关联业务信息列表，查询出所有的业务列表，并包含已关联的
     *
     * @param context
     * @return
     */
    public HandlerResult queryPageRelaBusiList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            List<PageRelaBusi> list = pageRelaBusiListService.queryBusiUsedList(param);
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
     * 查询页面关联业务信息列表，查询出关联的业务列表
     *
     * @param context
     * @return
     */
    public HandlerResult queryPageRelaBusiUsedList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            List<PageRelaBusi> list = pageRelaBusiListService.queryPageRelaBusiList(param);
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
