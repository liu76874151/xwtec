package com.xwtech.uomp.base.service.cache;

import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-8-23
 * Time: 下午5:17
 * To change this template use File | Settings | File Templates.
 */
public interface ICacheServiceFactory {

    ICacheService getCacheService(String key);

    void addCacheService(CacheServiceManageBean cacheServiceManageBean);

    void updateCacheService(CacheServiceManageBean cacheServiceManageBean);

    void deleteCacheService(String key);
}
