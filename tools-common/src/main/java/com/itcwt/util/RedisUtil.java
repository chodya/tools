package com.itcwt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可以应对高并发的redis客户端工具类
 *
 * @author cwt
 * @create by cwt on 2018-11-01 9:18
 */
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    protected static ReentrantLock lockJedis = new ReentrantLock();

    /** Redis连接池 */
    private static JedisPool JEDIS_POOL;

    static {
        // TODO 所有配置都不应该硬编码到代码中
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(5000);
        config.setMaxTotal(30);
        config.setMaxIdle(30);
        JEDIS_POOL = new JedisPool(config, "localhost", 6379);
    }

    /**
     * 从连接池中获取一个Redis连接。
     *
     * @return [Jeids]Redis的客户端连接
     */
    private static Jedis getConnection() {
        //断言 ，当前锁是否已经锁住，如果锁住了，就啥也不干，没锁的话就执行下面步骤
        assert ! lockJedis.isHeldByCurrentThread();
        lockJedis.lock();
        Jedis jedis = null;
        try {
            if (JEDIS_POOL != null) {
                jedis = JEDIS_POOL.getResource();
            }
        } catch (Exception e) {
            logger.error("Get jedis error : "+e);
        }finally{
            close(jedis);
            lockJedis.unlock();
        }
        return jedis;
    }

    public synchronized static String set(String key, String value){
        return getConnection().set(key, value);
    }

    public synchronized static String setex(String key, int seconds, String value){
        return getConnection().setex(key, seconds, value);
    }

    public synchronized static String get(String key){
        return getConnection().get(key);
    }

    public synchronized static boolean exists(String key){
        return getConnection().exists(key);
    }

    public synchronized static long expire(String key, int seconds){
        return getConnection().expire(key, seconds);
    }

    /**
     * 将连接还回连接池
     *
     * @param jedis
     */
    public static void close(final Jedis jedis){
        if (jedis != null && JEDIS_POOL != null){
            jedis.close();
        }
    }
}
