package com.xwtech.uomp.base.dao.cache;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-8-22
 * Time: 下午2:17
 * To change this template use File | Settings | File Templates.
 */

public class MemcachedDAO implements ICacheDAO {


    MemCachedClient memCachedClient;
    SockIOPool pool;

    public void setMemCachedClient(MemCachedClient memCachedClient) {
        this.memCachedClient = memCachedClient;
    }


    public void setPool(SockIOPool pool) {
        this.pool = pool;
    }

    
    public Object get(String key) {
        return memCachedClient.get(key);
    }

    
    public boolean add(String key, Object value) {
        return memCachedClient.add(key, value);
    }

    
    public boolean add(String key, Object value, long expireInMilliSeconds) {
        Date d = new Date(expireInMilliSeconds);
        return memCachedClient.add(key, value, d);
    }

    
    public boolean replace(String key, Object value) {
        return memCachedClient.replace(key, value);
    }

    
    public boolean replace(String key, Object value, long expireInMilliSeconds) {
        Date d = new Date(expireInMilliSeconds);
        return memCachedClient.replace(key, value, d);
    }

    
    public boolean delete(String key) {
        return memCachedClient.delete(key);
    }

    
    public void destory() {
        pool.shutDown();
    }

    
    public boolean flushAll() {
        return memCachedClient.flushAll();
    }
}
