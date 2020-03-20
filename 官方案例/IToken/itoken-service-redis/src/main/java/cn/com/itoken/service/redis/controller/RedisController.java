package cn.com.itoken.service.redis.controller;

import cn.com.itoken.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "put", method = RequestMethod.POST)
    public String put(String key, String value, long seconds){
        redisService.put(key, value, seconds);
        return "ok";  //存入成功返回ok
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String get(String key){
        Object o = redisService.get(key);  //取出数据

        if(o != null){  //存在则返回数据o
            String json = String.valueOf(o);
            return json;
        }
        return "not_ok";  //失败返回not_ok
    }
}
