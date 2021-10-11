package com.houyogju;

import redis.clients.jedis.Jedis;

/**
 * @author HouYongJu
 * @create 2021-09-25 10:29
 */
public class test1 {
    public static void main(String[] args) {
//        Jedis jedis = new Jedis("120.79.161.30", 6379);
//        String result = jedis.set("hello", "world");
//        String value = jedis.get("hello");
//        System.out.println(result);
//        System.out.println(value);
        Jedis jedis = null;

        try {

            String value = jedis.get("hello");
            System.out.println(value);
        } finally {
            if(jedis != null){
                jedis.close();
            }
        }

    }
}
