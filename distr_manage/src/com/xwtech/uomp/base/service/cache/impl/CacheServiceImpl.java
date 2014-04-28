package com.xwtech.uomp.base.service.cache.impl;

import com.xwtech.uomp.base.dao.cache.ICacheDAO;
import com.xwtech.uomp.base.service.cache.ICacheService;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-8-21
 * Time: 上午11:26
 * To change this template use File | Settings | File Templates.
 */
public class CacheServiceImpl implements ICacheService {

    ICacheDAO cacheDAO;

    public void setCacheDAO(ICacheDAO cacheDAO) {
        this.cacheDAO = cacheDAO;
    }

   
    public Object get(String key) {
        return cacheDAO.get(key);
    }

    
    public boolean add(String key, Object value) {
        return cacheDAO.add(key, value);
    }

    
    public boolean add(String key, Object value, long expireInMilliSeconds) {
        return cacheDAO.add(key, value, expireInMilliSeconds);
    }

    
    public boolean replace(String key, Object value) {
        return cacheDAO.replace(key, value);
    }

    
    public boolean replace(String key, Object value, long expireInMilliSeconds) {
        return cacheDAO.replace(key, value, expireInMilliSeconds);
    }

    
    public boolean delete(String key) {
        return cacheDAO.delete(key);
    }

    
    public void destory() {
        cacheDAO.destory();
    }

    
    public boolean flushAll() {
       return cacheDAO.flushAll();
    }
}
