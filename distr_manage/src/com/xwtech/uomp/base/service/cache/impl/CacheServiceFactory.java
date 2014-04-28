package com.xwtech.uomp.base.service.cache.impl;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.xwtech.uomp.base.dao.cache.ICacheDAO;
import com.xwtech.uomp.base.dao.cache.MemcachedDAO;
import com.xwtech.uomp.base.dao.cache.RedisDAO;
import com.xwtech.uomp.base.pojo.memcache.CacheServiceManageBean;
import com.xwtech.uomp.base.service.ICacheServiceManagerService;
import com.xwtech.uomp.base.service.cache.ICacheService;
import com.xwtech.uomp.base.service.cache.ICacheServiceFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: alabo
 * Date: 13-8-23
 * Time: 上午10:46
 * To change this template use File | Settings | File Templates.
 */
@Service("cacheServiceFactory")
public class CacheServiceFactory implements ICacheServiceFactory {

    /**
     * 初始化日志
     */
    private static final Logger logger = Logger.getLogger(CacheServiceFactory.class);

    private static Map<String, ICacheService> cacheServiceMap = new Hashtable<String, ICacheService>();

    @Autowired
    ICacheServiceManagerService cacheServiceManagerService;

    /**
     * 获得缓存服务
     *
     * @param key
     * @return
     */
    public ICacheService getCacheService(String key) {
        if (cacheServiceMap.get(key) != null) {
            return cacheServiceMap.get(key);
        }
        return null;
    }

    /**
     * 新增缓存服务
     *
     * @param cacheServiceManageBean
     */
    public void addCacheService(CacheServiceManageBean cacheServiceManageBean) {
        String key = cacheServiceManageBean.getNum();
        if (cacheServiceMap.get(key) == null) {
            ICacheDAO cacheDAO = constructMemcachedDAO(cacheServiceManageBean);
            CacheServiceImpl cacheService = new CacheServiceImpl();
            cacheService.setCacheDAO(cacheDAO);
            cacheServiceMap.put(key, cacheService);
        }
    }

    /**
     * 修改缓存服务
     *
     * @param cacheServiceManageBean
     */
    public void updateCacheService(CacheServiceManageBean cacheServiceManageBean) {
        String key = cacheServiceManageBean.getNum();
        if (cacheServiceMap.get(key) != null) {
            deleteCacheService(key);

            ICacheDAO cacheDAO = constructMemcachedDAO(cacheServiceManageBean);
            CacheServiceImpl cacheService = new CacheServiceImpl();
            cacheService.setCacheDAO(cacheDAO);
            cacheServiceMap.put(key, cacheService);
        }
    }

    /**
     * 删除缓存服务
     *
     * @param key
     */
    public void deleteCacheService(String key) {
        ICacheService cacheService = cacheServiceMap.get(key);
        //销毁缓存服务
        cacheService.destory();
        cacheServiceMap.remove(key);
    }

    /**
     * 刷新缓存服务
     *
     * @param cacheServiceManageBean
     */
    public void flushCacheService(CacheServiceManageBean cacheServiceManageBean) {
        String key = cacheServiceManageBean.getNum();
        if (cacheServiceMap.get(key) != null) {
            ICacheService cacheService = cacheServiceMap.get(key);
            cacheService.flushAll();
        }
    }

    /**
     * 初始化缓存服务工厂
     */
    @PostConstruct
    private void init() {
        List<CacheServiceManageBean> list = cacheServiceManagerService.queryCacheServiceList(new HashMap<String, Object>());
        for (CacheServiceManageBean cacheServiceManageBean : list) {
            String key = cacheServiceManageBean.getNum();
            String serverType = cacheServiceManageBean.getServerType();
            CacheServiceImpl cacheService = new CacheServiceImpl();
            if (serverType.equals("Redis")) {
                ICacheDAO cacheDAO = constructRedisDAO(cacheServiceManageBean);
                cacheService.setCacheDAO(cacheDAO);
            } else if (serverType.equals("Memcached")) {
                ICacheDAO cacheDAO = constructMemcachedDAO(cacheServiceManageBean);
                cacheService.setCacheDAO(cacheDAO);
            }
            cacheServiceMap.put(key, cacheService);
        }
    }

    /**
     * 构造memcached的DAO
     *
     * @param cacheServiceManageBean
     * @return
     */
    private ICacheDAO constructMemcachedDAO(CacheServiceManageBean cacheServiceManageBean) {
        try {
            String mainSleep = cacheServiceManageBean.getMainSleep();
            String maxConn = cacheServiceManageBean.getMaxConn();
            String maxIdle = cacheServiceManageBean.getMaxIdle();
            String minConn = cacheServiceManageBean.getMinConn();
            String nagle = cacheServiceManageBean.getNagle();
            String initConn = cacheServiceManageBean.getInitConn();
            String poolName = cacheServiceManageBean.getNum();
            String servers = cacheServiceManageBean.getServers();
            String socketConnectTo = cacheServiceManageBean.getSocketConnectTo();
            String socketTo = cacheServiceManageBean.getSocketTo();
            String typeNum = cacheServiceManageBean.getTypeNum();
            String weights = cacheServiceManageBean.getWeights();

            logger.info("初始化Memcached客户端...");


            MemCachedClient memCachedClient = new MemCachedClient(poolName);
            // 开始验证...
            // 缓存服务器和其权重是否配置验证
            if (servers == null || servers.trim().equals("")) {
                logger.error("服务器列表配置不能为空......缓存建立失败！");
            }
            if (weights == null || weights.trim().equals("")) {
                logger.error("服务器的权重配置不能为空......缓存建立失败！");
            }

            // 服务器列表和其权重
            String[] servers_array = servers.split(",");
            String[] strWeights = weights.split(",");
            Integer[] weights_array = new Integer[strWeights.length];
            for (int i = 0; i < strWeights.length; i++) {
                weights_array[i] = Integer.parseInt(strWeights[i]);
            }

            // 缓存服务器和其权重配置个数是否相等验证
            if (servers_array.length != weights_array.length) {
                logger.error("服务器和其权重配置个数不相等......缓存建立失败！");
            }

            // 初始连接数、最小和最大连接数以及最大处理时间验证
            if (initConn != null && !Pattern.compile("[0-9]*").matcher(initConn).matches()) {
                logger.error("初始连接数配置不能为字符......加载失败！");
            }
            if (minConn != null && !Pattern.compile("[0-9]*").matcher(minConn).matches()) {
                logger.error("最小连接数配置不能为字符......加载失败！");
            }
            if (maxConn != null && !Pattern.compile("[0-9]*").matcher(maxConn).matches()) {
                logger.error("最大连接数配置不能为字符......加载失败！");
            }
            if (maxIdle != null && !Pattern.compile("[0-9]*").matcher(maxIdle).matches()) {
                logger.error("最大处理时间配置不能为字符......加载失败！");
            }

            // 主线程的睡眠时间验证
            if (mainSleep != null && !Pattern.compile("[0-9]*").matcher(mainSleep).matches()) {
                logger.error("主线程睡眠时间配置不能为字符......加载失败！");
            }

            // TCP的参数，连接超时验证
            if (nagle != null && !nagle.trim().equals("") && !nagle.trim().equals("false") && !nagle.trim().equals("true")) {
                logger.error("TCP参数nagle配置只能为空值或者true或者false......加载失败！");
            }
            if (socketTo != null && !Pattern.compile("[0-9]*").matcher(socketTo).matches()) {
                logger.error("TCP参数socketTo配置不能为字符......加载失败！");
            }
            if (socketConnectTo != null && !Pattern.compile("[0-9]*").matcher(socketConnectTo).matches()) {
                logger.error("TCP参数socketConnectTo配置不能为字符......加载失败！");
            }

//		// 压缩设置验证
//		if (compressEnable != null && !compressEnable.trim().equals("")
//				&& !compressEnable.trim().equals("false")
//				&& !compressEnable.trim().equals("true"))
//		{
//			logger.error("是否压缩配置只能为空值或者true或者false......加载失败！");
//		}
//		if (compressThreshold != null
//				&& !Pattern.compile("[0-9]*").matcher(compressThreshold)
//						.matches())
//		{
//			logger.error("指定压缩大小(单位:K)配置不能为字符......加载失败！");
//		}
            // 验证结束...

            // 获取socke连接池的实例对象
            SockIOPool pool = SockIOPool.getInstance(poolName);
            // 设置服务器信息
            pool.setServers(servers_array);
            pool.setWeights(weights_array);

            // 设置初始连接数、最小和最大连接数以及最大处理时间
            if (initConn != null && !initConn.trim().equals("")) {
                pool.setInitConn(Integer.parseInt(initConn));
            }
            if (minConn != null && !minConn.trim().equals("")) {
                pool.setMinConn(Integer.parseInt(minConn));
            }
            if (maxConn != null && !maxConn.trim().equals("")) {
                pool.setMaxConn(Integer.parseInt(maxConn));
            }
            if (maxIdle != null && !maxIdle.trim().equals("")) {
                pool.setMaxIdle(Integer.parseInt(maxIdle));
            }

            // 设置主线程的睡眠时间
            if (mainSleep != null && !mainSleep.trim().equals("")) {
                pool.setMaintSleep(Long.parseLong(mainSleep));
            }

            // 设置TCP的参数，连接超时等
            if (nagle != null && !nagle.trim().equals("") && (nagle.trim().equals("false") || nagle.trim().equals("true"))) {
                pool.setNagle(Boolean.parseBoolean(nagle));
            }
            if (socketTo != null && !socketTo.trim().equals("")) {
                pool.setSocketTO(Integer.parseInt(socketTo));
            }
            if (socketConnectTo != null && !socketConnectTo.trim().equals("")) {
                pool.setSocketConnectTO(Integer.parseInt(socketConnectTo));
            }

            // 初始化连接池
            pool.initialize();

//		// 压缩设置，超过指定大小（单位为K）的数据都会被压缩
//		if (compressEnable != null
//				&& !compressEnable.trim().equals("")
//				&& (compressEnable.trim().equals("false") || compressEnable
//						.trim().equals("true")))
//		{
//			memCachedClient.setCompressEnable(Boolean
//					.parseBoolean(compressEnable));
//		}
//		if (compressThreshold != null && !compressThreshold.trim().equals(""))
//		{
//			memCachedClient.setCompressThreshold(Long
//					.parseLong(compressThreshold));
//		}

            MemcachedDAO memcachedDAO = new MemcachedDAO();
            memcachedDAO.setMemCachedClient(memCachedClient);
            memcachedDAO.setPool(pool);

            logger.info("Memcached客户端初始化成功!");
            return memcachedDAO;
        } catch (Exception e) {
            logger.error("Memcached客户端初始失败!");
            return null;
        }

    }

    /**
     * 构造redis的DAO
     *
     * @param cacheServiceManageBean
     * @return
     */
    private ICacheDAO constructRedisDAO(CacheServiceManageBean cacheServiceManageBean) {
        int redMaxactive = cacheServiceManageBean.getRedMaxactive() == null ? 300 : Integer.parseInt(cacheServiceManageBean.getRedMaxactive());
        int redMaxidle = cacheServiceManageBean.getRedMaxidle() == null ? 100 : Integer.parseInt(cacheServiceManageBean.getRedMaxidle());
        int redMaxwait = cacheServiceManageBean.getRedMaxwait() == null ? 1000 : Integer.parseInt(cacheServiceManageBean.getRedMaxwait());
        boolean redTestonborrow = cacheServiceManageBean.getRedTestonborrow() == null ? true : Boolean.getBoolean(cacheServiceManageBean.getRedTestonborrow());
        String redHost = cacheServiceManageBean.getRedHost();
        int redPort = cacheServiceManageBean.getRedPort() == null ? 6379 : Integer.parseInt(cacheServiceManageBean.getRedPort());
        int redTimeout = cacheServiceManageBean.getRedTimeout() == null ? 10000 : Integer.parseInt(cacheServiceManageBean.getRedTimeout());
        int redDefaultdb = cacheServiceManageBean.getRedDefaultdb() == null ? 0 : Integer.parseInt(cacheServiceManageBean.getRedDefaultdb());

        try {
            logger.info("初始化Redis客户端...");

            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            //大连接数
            jedisPoolConfig.setMaxActive(redMaxactive);
            //最大空闲数
            jedisPoolConfig.setMaxIdle(redMaxidle);
            //最大建立连接等待时间
            jedisPoolConfig.setMaxWait(redMaxwait);
            //指明是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
            jedisPoolConfig.setTestOnBorrow(redTestonborrow);


            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
            jedisConnectionFactory.setUsePool(true);
            //redis的服务器地址
            jedisConnectionFactory.setHostName(redHost);
            //redis的服务端口
            jedisConnectionFactory.setPort(redPort);
            //客户端超时时间单位是毫秒
            jedisConnectionFactory.setTimeout(redTimeout);
            //链接数据库
            jedisConnectionFactory.setDatabase(redDefaultdb);
            jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
            //初始化
            jedisConnectionFactory.afterPropertiesSet();


            RedisTemplate redisTemplate = new RedisTemplate();
            redisTemplate.setConnectionFactory(jedisConnectionFactory);
            //初始化
            redisTemplate.afterPropertiesSet();


            RedisDAO redisDAO = new RedisDAO();
            redisDAO.setRedisTemplate(redisTemplate);

            logger.info("Redis客户端初始成功!");

            return redisDAO;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis客户端初始失败!");
            return null;
        }
    }
}
