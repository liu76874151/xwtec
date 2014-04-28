package com.xwtech.uomp.base.dao.automated;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.memcache.CacheManageBean;
import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;

/**
 * @author huangfeilong
 * @ClassName: CacheManageMapper
 * @Description: 缓存设置管理
 * @date Mar 6, 2013 3:30:46 PM
 */
public interface CacheManageMapper {
    /**
     * @param cacheManageBean
     * @return List<CacheManageBean> 返回类型
     * @throws
     * @Title: queryCacheManageInfoList
     * @Description: 查询所有缓存设置信息
     */
    public List<CacheManageBean> queryCacheManageInfoList(CacheManageBean cacheManageBean);

    /**
     * @param cacheManageBean
     * @return void 返回类型
     * @throws
     * @Title: addCacheManageInfo
     * @Description: 添加缓存设置信息
     */
    public void addCacheManageInfo(CacheManageBean cacheManageBean);

    /**
     * @param memKey
     * @return List<CacheManageBean> 返回类型
     * @throws
     * @Title: queryCacheManageInfoById
     * @Description: 根据id查询缓存设置信息
     */
    public List<CacheManageBean> queryCacheManageInfoById(String memKey);

    /**
     * @param cacheManageBean
     * @return void 返回类型
     * @throws
     * @Title: modCacheManageInfo
     * @Description: 修改缓存设置信息
     */
    public void modCacheManageInfo(CacheManageBean cacheManageBean);

    /**
     * @param memKey
     * @return void 返回类型
     * @throws
     * @Title: delCacheManageInfo
     * @Description: 删除缓存设置信息
     */
    public void delCacheManageInfo(String memKey);

    /**
     * @param map
     * @return List<CacheServiceManageBean> 返回类型
     * @throws
     * @Title: queryCacheService
     * @Description: 清除缓存设置信息
     */
    public List<CacheServiceManageBean> queryCacheService(Map<String, Object> map);
}
