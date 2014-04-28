package com.xwtech.uomp.base.service.memcache;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;
import com.xwtech.uomp.base.pojo.memcache.CacheTypeManageBean;

/**
 * @author huangfeilong
 * @ClassName: ICacheTypeManageService
 * @Description: 缓存类型服务类
 * @date Mar 6, 2013 8:06:52 PM
 */
public interface ICacheTypeManageService {
    /**
     * @param cacheTypeInfo
     * @return List<CacheTypeManageBean> 返回类型
     * @throws
     * @Title: queryCacheTypeManageInfoList
     * @Description: 查询所有缓存类型信息
     */
    public List<CacheTypeManageBean> queryCacheTypeManageInfoList(CacheTypeManageBean cacheTypeInfo);

    /**
     * @param typeNum
     * @return CacheTypeManageBean 返回类型
     * @throws
     * @Title: queryCacheTypeInfoById
     * @Description: 根据id查询所有缓存类型信息
     */
    public CacheTypeManageBean queryCacheTypeInfoById(String typeNum);

    /**
     * @param cacheTypeInfo
     * @return void 返回类型
     * @throws
     * @Title: addCacheTypeInfo
     * @Description: 添加缓存类型信息
     */
    public void addCacheTypeInfo(CacheTypeManageBean cacheTypeInfo);

    /**
     * @param cacheTypeInfo
     * @return void 返回类型
     * @throws
     * @Title: modCacheTypeInfo
     * @Description: 修改缓存类型信息
     */
    public void modCacheTypeInfo(CacheTypeManageBean cacheTypeInfo);

    /**
     * @param typeNum
     * @return void 返回类型
     * @throws
     * @Title: delCacheTypeInfo
     * @Description: 删除缓存类型信息
     */
    public void delCacheTypeInfo(String typeNum);

    /**
     * @param map
     * @return List<CacheServiceManageBean> 返回类型
     * @throws
     * @Title: queryCacheService
     * @Description: 根据typeNum 查询出具体点的缓存
     */
    public List<CacheServiceManageBean> queryCacheService(Map<String, Object> map);
}
