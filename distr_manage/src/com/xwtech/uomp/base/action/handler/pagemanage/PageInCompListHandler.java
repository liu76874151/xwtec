package com.xwtech.uomp.base.action.handler.pagemanage;

import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.pagemanage.PageInComp;
import com.xwtech.uomp.base.service.pagemanage.IPageInCompListService;
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
 * Date: 13-7-23
 * Time: 上午11:06
 * To change this template use File | Settings | File Templates.
 */

@Component("H_pageInCompList")
public class PageInCompListHandler implements IHandler {

    @Autowired
    IPageInCompListService pageInCompListService;

    /**
     * 查询页面关联组件使用情况信息列表
     *
     * @param context
     * @return
     */
    public HandlerResult queryPageInCompList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            List<PageInComp> list = pageInCompListService.queryPageCompUsedList(param);
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
