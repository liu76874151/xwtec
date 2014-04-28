package com.xwtech.uomp.base.action.handler.memcache;

import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;
import com.xwtech.uomp.base.pojo.memcache.CacheTypeManageBean;
import com.xwtech.uomp.base.service.ICacheCleanService;
import com.xwtech.uomp.base.service.memcache.ICacheTypeManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangfeilong
 * @ClassName: CacheTypeManageHandler
 * @Description: 缓存类型管理类
 * @date Mar 6, 2013 3:21:12 PM
 */

@Component("H_cacheTypeManage")
public class CacheTypeManageHandler implements IHandler {

    @Autowired
    private ICacheTypeManageService cacheTypeManageService;

    @Autowired
    private ICacheCleanService cacheCleanService;

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryCacheTypeManageInfoList
     * @Description: 查询所有缓存类型信息
     */
    public HandlerResult queryCacheTypeManageInfoList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String typeNum = context.getRequest().getParameter("typeNum");
        String typeName = context.getRequest().getParameter("typeName");

        CacheTypeManageBean cacheTypeInfo = new CacheTypeManageBean();

        if (typeNum != null && !typeNum.equals("")) {
            cacheTypeInfo.setTypeNum(typeNum);
        }

        if (typeName != null && !typeName.equals("")) {
            cacheTypeInfo.setTypeName(typeName);
        }

        // 获取缓存类型信息
        List<CacheTypeManageBean> cacheTypeManageInfolist = null;
        result.setRetCode(IResultCode.SYS_SUCCESS);

        try {
            cacheTypeManageInfolist = cacheTypeManageService.queryCacheTypeManageInfoList(cacheTypeInfo);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
        }

        result.setRetObj(JSONArray.toJSONString(cacheTypeManageInfolist));
        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryCacheTypeInfoList
     * @Description: 查询所有缓存类型信息，返回一个list
     */
    public HandlerResult queryCacheTypeInfoList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();

        CacheTypeManageBean cacheTypeInfo = new CacheTypeManageBean();

        // 获取缓存类型信息
        List<CacheTypeManageBean> cacheTypeInfolist = null;
        Map<String, Object> map = new HashMap<String, Object>();
        result.setRetCode(IResultCode.SYS_SUCCESS);

        try {
            cacheTypeInfolist = cacheTypeManageService.queryCacheTypeManageInfoList(cacheTypeInfo);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
        }

        map.put("cacheTypeInfolist", cacheTypeInfolist);
        result.setRetObj(map);
        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryCacheTypeInfoById
     * @Description: 根据id查询缓存类型信息
     */
    public HandlerResult queryCacheTypeInfoById(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String typeNum = context.getRequest().getParameter("selectRowId");
        Map<String, Object> map = new HashMap<String, Object>();

        // 获取缓存配置信息
        CacheTypeManageBean cacheTypeInfo = cacheTypeManageService.queryCacheTypeInfoById(typeNum);

        map.put("cacheTypeInfo", cacheTypeInfo);
        result.setRetObj(map);
        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: addCacheTypeInfo
     * @Description: 添加缓存类型信息
     */
    public HandlerResult addCacheTypeInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String typeNum = context.getRequest().getParameter("typeNum").trim();
        String typeName = context.getRequest().getParameter("typeName").trim();
        String desc = context.getRequest().getParameter("desc").trim();

        CacheTypeManageBean cacheTypeInfo = new CacheTypeManageBean(typeNum, typeName, desc);

        result.setRetCode(IResultCode.SYS_SUCCESS);
        result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);

        try {
            cacheTypeManageService.addCacheTypeInfo(cacheTypeInfo);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.INSERT_INFO_FAILED);
        }
        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: modCacheTypeInfo
     * @Description: 修改缓存类型信息
     */
    public HandlerResult modCacheTypeInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String typeNum = context.getRequest().getParameter("typeNum").trim();
        String typeName = context.getRequest().getParameter("typeName").trim();
        String desc = context.getRequest().getParameter("desc").trim();

        CacheTypeManageBean cacheTypeInfo = new CacheTypeManageBean(typeName, typeNum, desc);

        result.setRetCode(IResultCode.SYS_SUCCESS);
        result.setSysCode(SystemCodeConstants.EDIT_INFO_SUCCEED);

        try {
            cacheTypeManageService.modCacheTypeInfo(cacheTypeInfo);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.EDIT_INFO_FAILED);
        }
        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: delCacheTypeInfo
     * @Description: 删除缓存类型信息
     */
    public HandlerResult delCacheTypeInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String typeNum = context.getRequest().getParameter("typeNum");

        result.setRetCode(IResultCode.SYS_SUCCESS);
        result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);

        try {
            cacheTypeManageService.delCacheTypeInfo(typeNum);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_INFO_FAILED);
        }
        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: cleanCache
     * @Description: 清除缓存
     */
    public HandlerResult cleanCache(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            String typeNum = context.getRequest().getParameter("typeNum");
            String isCleanAllFlag = context.getRequest().getParameter("isCleanAllFlag");

            if ("".equals(isCleanAllFlag) || (!"1".equals(isCleanAllFlag) && !"0".equals(isCleanAllFlag))
                    || ("0".equals(isCleanAllFlag) && "".equals(typeNum))) {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.DELETE_CACHE_FAILED);
            } else {
                // 根据typeNum 查询出具体点的缓存服务
                List<CacheServiceManageBean> toPageType = null;
                map.put("typeNum", typeNum);
                toPageType = cacheTypeManageService.queryCacheService(map);

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
            result.setRetCode(IResultCode.SYS_FAILED);
            result.setSysCode(SystemCodeConstants.DELETE_CACHE_FAILED);
        }

        return result;
    }
}
