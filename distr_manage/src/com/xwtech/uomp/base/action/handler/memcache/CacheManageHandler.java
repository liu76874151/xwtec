package com.xwtech.uomp.base.action.handler.memcache;

import com.alibaba.fastjson.JSONArray;
import com.xwtech.uomp.base.action.handler.HandlerRequestContext;
import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.action.handler.IHandler;
import com.xwtech.uomp.base.constants.IResultCode;
import com.xwtech.uomp.base.constants.SystemCodeConstants;
import com.xwtech.uomp.base.pojo.memcache.CacheManageBean;
import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;
import com.xwtech.uomp.base.service.ICacheCleanService;
import com.xwtech.uomp.base.service.memcache.ICacheManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangfeilong
 * @ClassName: CacheManageHandler
 * @Description: 缓存设置管理类
 * @date Mar 6, 2013 3:13:38 PM
 */
@Component("H_cacheManage")
public class CacheManageHandler implements IHandler {

    @Autowired
    private ICacheManageService cacheManageService;

    @Autowired
    private ICacheCleanService cacheCleanService;

    /**
     * 地区列表（江苏十三个地市简称）
     */
    private static final String[] areaList =
            {"XZDQ", "CZDQ", "LYGDQ", "SQDQ", "NJDQ", "ZJDQ", "YCDQ", "YZDQ", "TZDQ", "TZDQ", "NTDQ", "HADQ", "SZDQ"};

    /**
     * 品牌列表（动感地带、神州行、全球通 、默认品牌DEFAULT）
     */
    private static final String[] brandList =
            {"DEFAULT", "DGDD", "SZX", "QQT"};

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryCacheManageInfoList
     * @Description: 查询所有缓存配置信息
     */
    public HandlerResult queryCacheManageInfoList(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String memKey = context.getRequest().getParameter("memKey");
        String keyType = context.getRequest().getParameter("keyType");
        String isNeedParam = context.getRequest().getParameter("isNeedParam");

        CacheManageBean cacheManageInfo = new CacheManageBean();

        if (memKey != null && !memKey.equals("")) {
            cacheManageInfo.setMemKey(memKey);
        }

        if (keyType != null && !keyType.equals("")) {
            cacheManageInfo.setKeyType(keyType);
        }

        if (isNeedParam != null && !isNeedParam.equals("")) {
            cacheManageInfo.setIsNeedParam(isNeedParam);
        }

        // 获取缓存配置信息
        List<CacheManageBean> cacheManageInfolist = null;
        result.setRetCode(IResultCode.SYS_SUCCESS);

        try {
            // 获取缓存配置信息
            cacheManageInfolist = cacheManageService.queryCacheManageInfoList(cacheManageInfo);
        } catch (Exception e) {
            result.setRetCode(IResultCode.SYS_FAILED);
        }

        result.setRetObj(JSONArray.toJSONString(cacheManageInfolist));
        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: queryCacheManageInfoById
     * @Description: 根据id查询缓存配置信息
     */
    public HandlerResult queryCacheManageInfoById(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String memKey = context.getRequest().getParameter("selectRowId");
        Map<String, Object> map = new HashMap<String, Object>();

        // 获取缓存配置信息
        CacheManageBean cacheManageInfo = cacheManageService.queryCacheManageInfoById(memKey);

        map.put("cacheManageInfo", cacheManageInfo);
        result.setRetObj(map);
        return result;
    }

    /**
     * @param context
     * @return HandlerResult 返回类型
     * @throws
     * @Title: addCacheManageInfo
     * @Description: 添加缓存配置信息
     */
    public HandlerResult addCacheManageInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String memKey = context.getRequest().getParameter("memKey").trim();
        String cachedName = context.getRequest().getParameter("cachedName").trim();
        String daoName = context.getRequest().getParameter("daoName").trim();
        String daoMethod = context.getRequest().getParameter("daoMethod").trim();
        String isNeedParam = context.getRequest().getParameter("isNeedParam").trim();
        int expireInSeconds = Integer.parseInt(context.getRequest().getParameter("expireInSeconds"));
        String bz = context.getRequest().getParameter("bz").trim();
        String keyType = context.getRequest().getParameter("keyType").trim();

        CacheManageBean cacheManageBean = new CacheManageBean(memKey, cachedName, daoName, daoMethod, isNeedParam, expireInSeconds, bz, keyType);

        result.setRetCode(IResultCode.SYS_SUCCESS);
        result.setSysCode(SystemCodeConstants.INSERT_INFO_SUCCEED);

        try {
            cacheManageService.addCacheManageInfo(cacheManageBean);
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
     * @Title: modCacheManageInfo
     * @Description: 修改缓存配置信息
     */
    public HandlerResult modCacheManageInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String memKey = context.getRequest().getParameter("memKey").trim();
        String cachedName = context.getRequest().getParameter("cachedName").trim();
        String daoName = context.getRequest().getParameter("daoName").trim();
        String daoMethod = context.getRequest().getParameter("daoMethod").trim();
        String isNeedParam = context.getRequest().getParameter("isNeedParam").trim();
        int expireInSeconds = Integer.parseInt(context.getRequest().getParameter("expireInSeconds"));
        String bz = context.getRequest().getParameter("bz").trim();
        String keyType = context.getRequest().getParameter("keyType").trim();

        CacheManageBean cacheManageBean = new CacheManageBean(memKey, cachedName, daoName, daoMethod, isNeedParam, expireInSeconds, bz, keyType);

        result.setRetCode(IResultCode.SYS_SUCCESS);
        result.setSysCode(SystemCodeConstants.EDIT_INFO_SUCCEED);

        try {
            cacheManageService.modCacheManageInfo(cacheManageBean);
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
     * @Title: delCacheManageInfo
     * @Description: 删除缓存配置信息
     */
    public HandlerResult delCacheManageInfo(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();
        String memKey = context.getRequest().getParameter("memKey");

        result.setRetCode(IResultCode.SYS_SUCCESS);
        result.setSysCode(SystemCodeConstants.DELETE_INFO_SUCCEED);

        try {
            cacheManageService.delCacheManageInfo(memKey);
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
     * @Description: 删除缓存配置信息
     */
    public HandlerResult cleanCache(HandlerRequestContext context) {
        HandlerResult result = HandlerResult.newInstance();

        try {
            // 保存清除对象key list集合
            List<String> cleanKeyList = new ArrayList<String>();

            String memKey = context.getRequest().getParameter("memKey");

            // 当不存在参数，该值为"0"
            String memKeyVal = context.getRequest().getParameter("memKeyVal");

            // 用于标记当前是否是传递了具体的参数 0：没有传递，1：传递
            String paraFlag = context.getRequest().getParameter("paraFlag");

            Map<String, Object> map = new HashMap<String, Object>();

            // 提示必须填写子段为空
            if ("".equals(memKey) && "".equals(memKeyVal)) {
                result.setRetCode(IResultCode.SYS_FAILED);
                result.setSysCode(SystemCodeConstants.CHECK_FIELD_EMPTY);
            } else {
                // 查询出具体点的缓存服务
                List<CacheServiceManageBean> toPageType = null;
                map.put("memKey", memKey);
                toPageType = cacheManageService.queryCacheService(map);

                if (toPageType == null || toPageType.size() == 0) {
                    result.setRetCode(IResultCode.SYS_FAILED);
                    result.setSysCode(SystemCodeConstants.CACHE_NOT_FIND);
                } else {
                    boolean cleanSuccess = false;

                    if ("0".equals(memKeyVal)) {
                        cleanKeyList.add(memKey);
                    }
                    // 有参数
                    else {
                        // 有参数但是没有传递，此时刷新该类型的参数
                        if ("0".equals(paraFlag)) {
                            String paraFlagVal = toPageType.get(0).getParaFlag();
                            cleanKeyList = getPrapKeyList(paraFlagVal, memKey, cleanKeyList);
                        }
                        // 有参数也传递了实际的参数，此刷新具体的参数
                        else if ("1".equals(paraFlag)) {
                            cleanKeyList.add(memKeyVal);
                        }
                    }

                    if (cleanKeyList != null && cleanKeyList.size() > 0) {
                        cleanSuccess = cacheCleanService.cleanChacheByList(toPageType, result, cleanKeyList);
                    } else {
                        result.setRetCode(IResultCode.SYS_FAILED);
                        result.setSysCode(SystemCodeConstants.CACHE_NOT_FIND);
                    }

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

    /**
     * @param paraKey      参数类型（只标记区域和品牌） 空或者0：无参数。1：区域。2：品牌。3：区域+品牌
     * @param memKey
     * @param cleanKeyList
     * @return List<String> 返回类型
     * @throws
     * @Title: getPrapKeyList
     * @Description: 当有参数，但没有传递参数时，刷新这类缓存
     */
    private static List<String> getPrapKeyList(String paraKey, String memKey, List<String> cleanKeyList) {
        String splitStr = "_\\{";
        if (memKey != null && !"".equals(memKey)) {
            String splitaArr[] = memKey.split(splitStr);
            String memKeyVal = splitaArr[0];
            if ("0".equals(paraKey)) {
                return cleanKeyList;
            }
            //1：区域。
            else if ("1".equals(paraKey)) {
                for (int i = 0; i < areaList.length; i++) {
                    cleanKeyList.add(memKeyVal + "_" + areaList[i]);
                }
            }
            //2：品牌
            else if ("2".equals(paraKey)) {
                for (int i = 0; i < brandList.length; i++) {
                    cleanKeyList.add(memKeyVal + "_" + brandList[i]);
                }
            }
            //3：区域+品牌
            else if ("3".equals(paraKey)) {
                for (int i = 0; i < areaList.length; i++) {
                    for (int j = 0; j < brandList.length; j++) {
                        cleanKeyList.add(memKeyVal + "_" + areaList[i] + "_" + brandList[j]);
                    }
                }
            }
            //4：区域+品牌+手机号（当前为DEFAULT）
            else if ("4".equals(paraKey)) {
                for (int i = 0; i < areaList.length; i++) {
                    for (int j = 0; j < brandList.length; j++) {
                        cleanKeyList.add(memKeyVal + "_" + areaList[i] + "_" + brandList[j] + "_DEFAULT");
                    }
                }
            }
        } else {
            return cleanKeyList;
        }

        return cleanKeyList;
    }
}
