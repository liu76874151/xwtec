package com.xwtech.uomp.base.service;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;

/**
 * @author yugonggan
 * @ClassName: ICacheServiceManagerService
 * @Description:
 * @date Mar 7, 2013 11:02:03 AM
 */
public interface ICacheServiceManagerService {


    /**
     * @param map
     * @return List<CacheServiceManageBean>
     * @throws
     * @Title: queryCacheServiceList
     * @Description: TODO()
     */
    public List<CacheServiceManageBean> queryCacheServiceList(Map<String, Object> map);

    public void addCacheService(CacheServiceManageBean csm);

    public CacheServiceManageBean queryCacheServiceById(String id);

    public void updateCacheService(CacheServiceManageBean csm);

    public void deleteCacheService(String id);

}
