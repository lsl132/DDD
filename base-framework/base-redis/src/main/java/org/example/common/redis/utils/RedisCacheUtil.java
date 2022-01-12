package org.example.common.redis.utils;

import org.example.common.redis.constant.RedisConstant;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @Describe : 关于Redis缓存管控
 * @Author : SHK
 * @Date : 2021/12/2 16:10
 */
public class RedisCacheUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;



    public void putCache(String key, Object hk, Object val) {
        Long len = redisTemplate.opsForHash().lengthOfValue(key, hk);

//        if(len >= RedisConstant.CACHE_LIST_SIZE) {
//            redisTemplate.opsForHash().
//        }


    }



}
