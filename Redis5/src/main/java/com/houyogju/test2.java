package com.houyogju;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author HouYongJu
 * @create 2021-09-25 11:03
 */
public class test2 {
    public static void main(String[] args) {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "120.79.161.30", 6379);

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            String value = jedis.get("hello");
            System.out.println(value);
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
}
