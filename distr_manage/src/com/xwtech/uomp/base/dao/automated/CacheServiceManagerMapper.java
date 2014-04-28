package com.xwtech.uomp.base.dao.automated;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;

/**
 * @author yugonggan
 * @ClassName: CacheServiceManagerMapper
 * @Description: TODO(缓存服务管理)
 * @date Mar 11, 2013 10:52:11 AM
 */
public interface CacheServiceManagerMapper {

    /**
     * @param map
     * @return List<CacheServiceManageBean> 返回类型
     * @throws
     * @Title: queryCacheServiceList
     * @Description: TODO(查询缓存服务)
     */
    public List<CacheServiceManageBean> queryCacheServiceList(Map<String, Object> map);

    /**
     * @param csm
     * @return void 返回类型
     * @throws
     * @Title: addCacheService
     * @Description: TODO(新增缓存服务)
     */
    public void addCacheService(CacheServiceManageBean csm);

    /**
     * @param id
     * @return CacheServiceManageBean 返回类型
     * @throws
     * @Title: queryCacheServiceById
     * @Description: TODO(根据ID查询缓存服务)
     */
    public CacheServiceManageBean queryCacheServiceById(String id);

    /**
     * @param csm
     * @return void 返回类型
     * @throws
     * @Title: updateCacheService
     * @Description: TODO(缓存服务更新)
     */
    public void updateCacheService(CacheServiceManageBean csm);

    /**
     * @param id
     * @return void 返回类型
     * @throws
     * @Title: deleteCacheService
     * @Description: TODO(删除缓存服务)
     */
    public void deleteCacheService(String id);
}
