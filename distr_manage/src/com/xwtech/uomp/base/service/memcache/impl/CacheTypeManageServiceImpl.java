package com.xwtech.uomp.base.service.memcache.impl;

import com.xwtech.uomp.base.dao.automated.CacheTypeManageMapper;
import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;
import com.xwtech.uomp.base.pojo.memcache.CacheTypeManageBean;
import com.xwtech.uomp.base.service.memcache.ICacheTypeManageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author huangfeilong
 * @ClassName: CacheTypeManageServiceImpl
 * @Description: 缓存类型服务实现类
 * @date Mar 6, 2013 8:08:39 PM
 */
@Service("cacheTypeManageService")
public class CacheTypeManageServiceImpl implements ICacheTypeManageService {
    protected static final Logger log = Logger.getLogger(CacheTypeManageServiceImpl.class);

    @Autowired
    private CacheTypeManageMapper cacheTypeManageMapper;

    /*
     * <p>Title: queryCacheTypeManageInfoList</p>
     * <p>Description: 查询所有缓存类型信息</p>
     * @param cacheTypeInfo
     * @return
     * @see com.xwtech.uomp.base.service.ICacheTypeManageService#queryCacheTypeManageInfoList(com.xwtech.uomp.base.pojo.memcache.CacheTypeManageBean)
     */
    public List<CacheTypeManageBean> queryCacheTypeManageInfoList(CacheTypeManageBean cacheTypeInfo) {
        return cacheTypeManageMapper.queryCacheTypeManageInfoList(cacheTypeInfo);
    }

    /*
     * <p>Title: queryCacheTypeInfoById</p>
     * <p>Description: 根据id查询所有缓存类型信息</p>
     * @param typeNum
     * @return
     * @see com.xwtech.uomp.base.service.ICacheTypeManageService#queryCacheTypeInfoById(java.lang.String)
     */
    public CacheTypeManageBean queryCacheTypeInfoById(String typeNum) {
        return cacheTypeManageMapper.queryCacheTypeInfoById(typeNum);
    }

    /*
     * <p>Title: addCacheTypeInfo</p>
     * <p>Description: 添加缓存类型信息</p>
     * @param cacheTypeInfo
     * @see com.xwtech.uomp.base.service.ICacheTypeManageService#addCacheTypeInfo(com.xwtech.uomp.base.pojo.memcache.CacheTypeManageBean)
     */
    public void addCacheTypeInfo(CacheTypeManageBean cacheTypeInfo) {
        cacheTypeManageMapper.addCacheTypeInfo(cacheTypeInfo);
    }

    /*
     * <p>Title: modCacheTypeInfo</p>
     * <p>Description: 修改缓存类型信息</p>
     * @param cacheTypeInfo
     * @see com.xwtech.uomp.base.service.ICacheTypeManageService#modCacheTypeInfo(com.xwtech.uomp.base.pojo.memcache.CacheTypeManageBean)
     */
    public void modCacheTypeInfo(CacheTypeManageBean cacheTypeInfo) {
        cacheTypeManageMapper.modCacheTypeInfo(cacheTypeInfo);
    }

    /*
     * <p>Title: delCacheTypeInfo</p>
     * <p>Description: 删除缓存类型信息</p>
     * @param typeNum
     * @see com.xwtech.uomp.base.service.ICacheTypeManageService#delCacheTypeInfo(java.lang.String)
     */
    public void delCacheTypeInfo(String typeNum) {
        cacheTypeManageMapper.delCacheTypeInfo(typeNum);
    }

    /*
     * <p>Title: queryCacheService</p>
     * <p>Description: 根据typeNum 查询出具体点的缓存</p>
     * @param map
     * @return
     * @see com.xwtech.uomp.base.service.ICacheTypeManageService#queryCacheService(java.util.Map)
     */
    public List<CacheServiceManageBean> queryCacheService(Map<String, Object> map) {
        return cacheTypeManageMapper.queryCacheService(map);
    }
}
