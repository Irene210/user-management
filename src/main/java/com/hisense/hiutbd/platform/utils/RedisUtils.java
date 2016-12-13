package com.hisense.hiutbd.platform.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOperations;
	
    @Resource(name="redisTemplate")
    private HashOperations<String, String, String> hashOperations;
    
    @Resource(name="redisTemplate")
    private SetOperations<String, String> setOperations;
    
    
    public Set<String> keys(String pattern){
    	return redisTemplate.keys(pattern);
    }
    
    public Long incr(String key,int step){
    	return valueOperations.increment(key, step);
    }
    
    public String get(String key){
    	return valueOperations.get(key);
    }
    
    public void set(String key, String value){
    	valueOperations.set(key, value);
    }
    
    public Boolean isMember(String key, String value){
    	return setOperations.isMember(key, value);
    }
    
    public Set<String> members(String key){
    	return setOperations.members(key);
    }
    
    public Long add(String key, String... value){
    	return setOperations.add(key, value);
    }
    
    public Long size(String key){
    	return setOperations.size(key);
    }
    
    public void expire(String key, long timeout, TimeUnit unit){
    	redisTemplate.expire(key, timeout, unit);
    }
    
    public void expireAt(String key, Date date){
    	redisTemplate.expireAt(key, date);
    }
    
    public void delete(String key){
    	redisTemplate.delete(key);
    }
    
    public void hset(String key, String field, String value){
    	hashOperations.put(key, field, value);
    }
    
    public String hget(String key, String field){
    	return hashOperations.get(key, field);
    }
    
    public boolean hasKey(String key){
    	return redisTemplate.hasKey(key);
    }
    
    public Long incr(final String key){
    	return redisTemplate.execute(new RedisCallback<Long>(){

			@Override
			public Long doInRedis(RedisConnection conn) throws DataAccessException {
				return conn.incr(key.getBytes());
			}
    		
    	});
    }
	
    
    public void sendMessage(String channel, String  message){
    	System.out.println("发送主题消息："+message);
    	redisTemplate.convertAndSend(channel, message);
    }
    
}
