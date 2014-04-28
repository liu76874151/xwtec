package com.xwtech.uomp.base.dao.cache;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-8-20
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */
public class RedisDAO implements ICacheDAO {

    RedisTemplate redisTemplate;
    JedisConnectionFactory jedisConnectionFactory;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        this.jedisConnectionFactory = jedisConnectionFactory;
    }

    
    public boolean add(final String key, final Object value) {
        redisTemplate.execute(new RedisCallback<Object>() {
            
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getValueSerializer().serialize(value));
                return true;
            }
        });
        return false;
    }

    
    public boolean add(final String key, final Object value, final long expireInMilliSeconds) {
        redisTemplate.execute(new RedisCallback<Object>() {
            
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(redisTemplate.getStringSerializer().serialize(key), expireInMilliSeconds, redisTemplate.getValueSerializer().serialize(value));
                return true;
            }
        });
        return false;
    }

    
    public boolean replace(final String key, final Object value) {
        return add(key, value);   //redis没有replace的概念，add即为替换
    }

    
    public boolean replace(final String key, final Object value, final long expireInMilliSeconds) {
        return add(key, value, expireInMilliSeconds);   //redis没有replace的概念，add即为替换
    }

    
    public Object get(final String key) {
        return redisTemplate.execute(new RedisCallback<Object>() {
            
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key_bytes = redisTemplate.getStringSerializer().serialize(key);
                if (connection.exists(key_bytes)) {
                    byte[] value = connection.get(key_bytes);
                    Object object = redisTemplate.getValueSerializer().deserialize(value);
                    return object;
                }
                return null;
            }
        });
    }

    
    public boolean delete(final String key) {
        redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) {
                connection.del(redisTemplate.getStringSerializer().serialize(key));
                return true;
            }
        });
        return false;
    }

    
    public void destory() {
        jedisConnectionFactory.destroy();
    }

    
    public boolean flushAll() {
        try {
            redisTemplate.getConnectionFactory().getConnection().flushAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
