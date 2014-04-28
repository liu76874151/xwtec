package com.xwtech.uomp.base.service.impl;

import com.xwtech.uomp.base.dao.automated.CacheServiceManagerMapper;
import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;
import com.xwtech.uomp.base.service.ICacheServiceManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("cacheServiceManagerService")
public class CacheServiceManagerServiceImpl implements ICacheServiceManagerService {
    @Autowired
    CacheServiceManagerMapper cacheServiceManagerMapper;


    public List<CacheServiceManageBean> queryCacheServiceList(Map<String, Object> map) {
        return cacheServiceManagerMapper.queryCacheServiceList(map);
    }


    public void addCacheService(CacheServiceManageBean csm) {
        cacheServiceManagerMapper.addCacheService(csm);
    }


    public CacheServiceManageBean queryCacheServiceById(String id) {
        return cacheServiceManagerMapper.queryCacheServiceById(id);
    }


    public void updateCacheService(CacheServiceManageBean csm) {
        cacheServiceManagerMapper.updateCacheService(csm);
    }


    public void deleteCacheService(String id) {
        cacheServiceManagerMapper.deleteCacheService(id);
    }

}
