package com.xwtech.uomp.base.dao.cache;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-8-20
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
public interface ICacheDAO {
    Object get(String key);

    boolean add(String key, Object value);

    boolean add(String key, Object value, long expireInMilliSeconds);

    boolean replace(String key, Object value);

    boolean replace(String key, Object value, long expireInMilliSeconds);

    boolean delete(String key);

    void destory();

    boolean flushAll();
}
