package com.xwtech.uomp.base.dao.automated;

import java.util.List;
import java.util.Map;

import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;
import com.xwtech.uomp.base.pojo.memcache.CacheTypeManageBean;

public interface CacheTypeManageMapper {
    public List<CacheTypeManageBean> queryCacheTypeManageInfoList(CacheTypeManageBean cacheTypeInfo);

    public void addCacheTypeInfo(CacheTypeManageBean cacheTypeInfo);

    public CacheTypeManageBean queryCacheTypeInfoById(String typeNum);

    public void modCacheTypeInfo(CacheTypeManageBean cacheTypeInfo);

    public void delCacheTypeInfo(String typeNum);

    public List<CacheServiceManageBean> queryCacheService(Map<String, Object> map);
}
