package com.xwtech.uomp.base.action.handler.pagemanage;

import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.pagemanage.PageTmpInfo;
import com.xwtech.uomp.base.service.pagemanage.IPageGenerateService;
import com.xwtech.uomp.base.service.pagemanage.IPageStaticGenerateService;
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
 * Date: 13-7-25
 * Time: 上午9:54
 * To change this template use File | Settings | File Templates.
 */
@Component("H_pageGenerate")
public class PageGenerateHandler implements IHandler {

    @Autowired
    IPageGenerateService pageGenerateService;

    @Autowired
    IPageTmpInfoService pageTmpInfoService;

    @Autowired
    IPageStaticGenerateService pageStaticGenerateService;

    /**
     * 查询用于生成页面的页面模板信息列表
     *
     * @param context
     * @return
     */
    public HandlerResult queryPageTmpInfoListForGen(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> param = BeanUtil.getMapFromRequest(request);

        try {
            List<PageTmpInfo> list = pageTmpInfoService.queryPageTmpInfoListForGen(param);

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
     * 生成单个页面
     *
     * @param context
     * @return
     */
    public HandlerResult generatePages(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        HttpServletRequest request = context.getRequest();

        Map<String, String> paramMap = BeanUtil.getMapFromRequest(request);

        try {
            pageGenerateService.generatePages(paramMap);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
        }

        return result;
    }

    /**
     * 生成所有页面
     *
     * @param context
     * @return
     */
    public HandlerResult generateAllPages(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();

        try {
            pageGenerateService.generateAllPages();
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
        }
        return result;
    }

    /**
     * 生成所有页面
     *
     * @param context
     * @return
     */
    public HandlerResult generateAllStaticPages(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        try {
            pageStaticGenerateService.generateAllStaticPages("02");//渠道代码02代表掌厅
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
        }
        return result;
    }
}
