package com.xwtech.uomp.base.service.cache;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-8-21
 * Time: 上午11:25
 * To change this template use File | Settings | File Templates.
 */
public interface ICacheService {
    Object get(String key);

    boolean add(String key, Object value);

    boolean add(String key, Object value, long expireInMilliSeconds);

    boolean replace(String key, Object value);

    boolean replace(String key, Object value, long expireInMilliSeconds);

    boolean delete(String key);

    void destory();

    boolean flushAll();
}
