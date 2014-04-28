package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.action.handler.HandlerResult;
import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;
import com.xwtech.uomp.base.service.ICacheCleanService;
import com.xwtech.uomp.base.service.cache.ICacheService;
import com.xwtech.uomp.base.service.cache.ICacheServiceFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 项目名称：js_obsh_manage 类名称：CacheCleaneServiceImpl 类描述： 缓存清除类 创建人：zangjianhua
 * 创建时间：2012-5-23 下午07:14:17 修改人：Administrator 修改时间：2012-5-23 下午07:14:17 修改备注：
 */
@Service("cacheCleanService")
public class CacheCleaneServiceImpl implements ICacheCleanService {

    /**
     * 初始化日志
     */
    private static final Logger log = Logger.getLogger(CacheCleaneServiceImpl.class);

    @Autowired
    ICacheServiceFactory cacheServiceFactory;

    
    public boolean cleanChacheByList(List<CacheServiceManageBean> serviceList, HandlerResult result, List<String> keyList) throws Exception {
        if (serviceList == null || serviceList.isEmpty()) {
            return false;
        }

        String resultMsg = "清除缓存:\n";
        try {
            for (CacheServiceManageBean csmb : serviceList) {
                String poolName = csmb.getNum();
                ICacheService cacheService = cacheServiceFactory.getCacheService(poolName);

                if (keyList == null || keyList.isEmpty()) {
                    if (cacheService.flushAll()) {
                        resultMsg += "  " + poolName + " 成功！\r";
                    } else {
                        resultMsg += "  " + poolName + " 失败！\r";
                    }
                } else {
                    for (String key : keyList) {
                        if (cacheService.delete(key)) {
                            resultMsg += " " + key + " 成功！\r";
                        } else {
                            resultMsg += " " + key + " 失败！\r";
                        }
                    }
                }
            }
            result.setResMsg(resultMsg);
            return true;
        } catch (Exception e) {
            log.error("缓存清理失败" + e.getMessage());
            throw e;
        }
    }

}
