package com.xwtech.uomp.base.action.handler.memcache;

import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;
import com.xwtech.uomp.base.service.ICacheCleanService;
import com.xwtech.uomp.base.service.ICacheServiceManagerService;
import com.xwtech.uomp.base.service.cache.ICacheServiceFactory;
import com.xwtech.uomp.base.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangfeilong
 * @ClassName: CacheServieManagerHandler
 * @Description: 缓存服务管理
 * @date Mar 6, 2013 3:23:27 PM
 */
@Component("H_cacheService")
public class CacheServieManagerHandler implements IHandler {
    /**
     * 初始化日志
     */
    private static final Logger log = Logger.getLogger(CacheServieManagerHandler.class);

    @Autowired
    ICacheServiceManagerService cacheServiceManagerService;

    @Autowired
    ICacheServiceFactory cacheServiceFactory;

    @Autowired
    ICacheCleanService cacheCleanService;

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryCacheServiceList
     * @Description: 查询缓存服务列表
     */
    public HandlerResult queryCacheServiceList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String num = context.getRequest().getParameter("num");
        String typeNum = context.getRequest().getParameter("typeNum");
        Map<String, Object> map = new HashMap<String, Object>();
        // mody by yugonggan begin 模糊查询
        if (null != num) {
            num = num.trim();
            num = num.replaceAll("_", "/_");
            num = num.replaceAll("%", "/%");
        }
        // mody by yugonggan end 模糊查询

        map.put("num", num);
        map.put("typeNum", typeNum);

        List<CacheServiceManageBean> beanList = null;

        try {
            beanList = cacheServiceManagerService.queryCacheServiceList(map);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
            result.setRetCode(IResultCode.SYS_SUCCESS);

        } catch (Exception e) {
            result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
            result.setRetCode(IResultCode.SYS_FAILED);

        }

        result.setRetObj(JSONArray.toJSONString(beanList));
        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: saveCacheService
     * @Description: 保存缓存服务
     */
    public HandlerResult saveCacheService(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        CacheServiceManageBean csm = new CacheServiceManageBean();
        String num = context.getRequest().getParameter("num");

        if (null == num || "".equals(num)) {
            result.setSysCode(SystemCodeConstants.CHECK_FIELD_EMPTY);
            result.setRetCode(IResultCode.SYS_FAILED);
        } else {
            CacheServiceManageBean cacheServiceManageBean = cacheServiceManagerService.queryCacheServiceById(num);
            if (StringUtil.isNull(cacheServiceManageBean)) {
                // 取参数组装
                csm.setNum(context.getRequest().getParameter("num"));
                csm.setTypeNum(context.getRequest().getParameter("typeNum"));
                csm.setServers(context.getRequest().getParameter("servers"));
                csm.setWeights(context.getRequest().getParameter("weights"));
                csm.setInitConn(context.getRequest().getParameter("initConn"));
                csm.setMinConn(context.getRequest().getParameter("minConn"));
                csm.setMaxConn(context.getRequest().getParameter("maxConn"));
                csm.setMaxIdle(context.getRequest().getParameter("maxIdle"));
                csm.setMainSleep(context.getRequest().getParameter("mainSleep"));
                csm.setNagle(context.getRequest().getParameter("nagle"));
                csm.setSocketTo(context.getRequest().getParameter("socketTo"));
                csm.setSocketConnectTo(context.getRequest().getParameter("socketConnectTo"));
                try {
                    //数据库中插入缓存服务数据
                    cacheServiceManagerService.addCacheService(csm);
                    //缓存服务工厂新建缓存服务
                    cacheServiceFactory.addCacheService(csm);
                    result.setRetCode(IResultCode.SYS_SUCCESS);
                    result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);
                } catch (Exception e) {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
                }
            } else {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.CHECK_FIELD_EXIST);
            }

        }

        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryCacheServiceById
     * @Description: 查询缓存服务
     */
    public HandlerResult queryCacheServiceById(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String id = context.getRequest().getParameter("selectRowId");
        try {

            CacheServiceManageBean csm = cacheServiceManagerService.queryCacheServiceById(id);
            result.setRetObj(csm);
            result.setSysCode(SystemCodeConstants.QUERY_INFO_SUCCEED);
            result.setRetCode(IResultCode.SYS_SUCCESS);
        } catch (Exception ex) {
            result.setSysCode(SystemCodeConstants.QUERY_INFO_FAILED);
            result.setRetCode(IResultCode.SYS_FAILED);
        }

        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: updateCacheService
     * @Description: 更新缓存服务
     */
    public HandlerResult updateCacheService(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        CacheServiceManageBean csm = new CacheServiceManageBean();
        // 取参数组装
        csm.setNum(context.getRequest().getParameter("num"));
        csm.setTypeNum(context.getRequest().getParameter("typeNum"));
        csm.setServers(context.getRequest().getParameter("servers"));
        csm.setWeights(context.getRequest().getParameter("weights"));
        csm.setInitConn(context.getRequest().getParameter("initConn"));
        csm.setMinConn(context.getRequest().getParameter("minConn"));
        csm.setMaxConn(context.getRequest().getParameter("maxConn"));
        csm.setMaxIdle(context.getRequest().getParameter("maxIdle"));
        csm.setMainSleep(context.getRequest().getParameter("mainSleep"));
        csm.setNagle(context.getRequest().getParameter("nagle"));
        csm.setSocketTo(context.getRequest().getParameter("socketTo"));
        csm.setSocketConnectTo(context.getRequest().getParameter("socketConnectTo"));

        try {
            //数据库中更新缓存服务数据
            cacheServiceManagerService.updateCacheService(csm);
            //缓存服务工厂更新缓存服务
            cacheServiceFactory.updateCacheService(csm);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.UPDATE_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.UPDATE_INFO_FAILED);
        }

        result.setRetObj(csm);
        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: deleteCacheService
     * @Description: 删除缓存服务
     */
    public HandlerResult deleteCacheService(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String id = context.getRequest().getParameter("id");
        try {
            //数据库删除缓存服务数据
            cacheServiceManagerService.deleteCacheService(id);
            //缓存服务工厂删除缓存服务
            cacheServiceFactory.deleteCacheService(id);
            result.setRetCode(IResultCode.SYS_SUCCESS);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
        }

        return result;
    }

    /**
     * 清除（所有）缓存
     *
     * @param context
     * @return
     */
    public HandlerResult cleanCache(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            // 当删除全部时，num为空
            String num = context.getRequest().getParameter("selectRowId");
            // 判断是否为删除全部。0：删除单个，1：删除全部
            String isCleanAllFlag = context.getRequest().getParameter("isCleanAllFlag");
            // 提示必须填写子段为空
            if ("".equals(isCleanAllFlag) || (!"0".equals(isCleanAllFlag) && !"1".equals(isCleanAllFlag)) || ("0".equals(isCleanAllFlag) && "".equals(num))) {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.CHECK_FIELD_EMPTY);
            } else {
                // 根据Num 查询出具体点的缓存服务
                map.put("num", num);
                map.put("typeNum", "");
                List<CacheServiceManageBean> toPageType = null;
                toPageType = cacheServiceManagerService.queryCacheServiceList(map);
                if (toPageType == null || toPageType.size() == 0) {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.CACHE_NOT_FIND);
                } else {
                    boolean cleanSuccess = cacheCleanService.cleanChacheByList(toPageType, result, null);
                    if (cleanSuccess) {
                        result.setRetCode(IResultCode.SYS_SUCCESS);
                        result.setSysCode(SystemCodeConstants.DELETE_CACHE_SUCCEED);
                    } else {
                        result.setRetCode(IResultCode.SYS_FAILED);
                        result.setSysCode(SystemCodeConstants.DELETE_CACHE_FAILED);
                    }

                }

            }
        } catch (Exception e) {
            log.error(e.getMessage());
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_CACHE_FAILED);
        }
        return result;
    }
}
