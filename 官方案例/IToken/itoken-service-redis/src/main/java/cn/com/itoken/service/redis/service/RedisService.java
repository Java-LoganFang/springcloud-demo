package cn.com.itoken.service.redis.service;

public interface RedisService {

    /**
     * 数据存入redis
     * @param key
     * @param value
     * @param seconds 超时时间
     */
    public void put(String key, Object value, long seconds);

    /**
     * 从redis获取数据
     * @param key
     */
    public Object get(String key);
}
