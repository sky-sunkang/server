package com.sunkang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * redis的服务
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入redis
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            ValueOperations<Serializable, Object> vo = redisTemplate.opsForValue();
            vo.set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    /**
     * 写入redis ,并设置有效时间
     * @param key
     * @param value
     * @param expire 秒
     * @return
     */
    public boolean set(String key, Object value,Long expire) {
        try {
            ValueOperations<Serializable, Object> vo = redisTemplate.opsForValue();
            vo.set(key, value);
            redisTemplate.expire(key,expire,TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 查询
     * @param key
     * @return
     */
    public Object get(String key) {
        ValueOperations<Serializable, Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

    /**
     * 删除
     * @param key
     * @return
     */
    public boolean delete(String key){
        try {
            redisTemplate.delete(key);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    /**
     * 对比token
     * @param key
     * @param value
     * @return
     */
    public boolean existsToken(String key,String value){
        try {
            String str=get(key)+"";
            if (str!=null&&str.equals(value)){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }
}
