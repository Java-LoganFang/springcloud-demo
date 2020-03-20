package cn.com.itoken.service.sso.service.consumer.fallback;

import cn.com.itoken.service.sso.service.consumer.RedisService;
import cn.com.itoken.common.hystrix.Fallback;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public String put(String key, String value, long seconds) {
        return Fallback.badGateway();
    }

    @Override
    public String get(String key) {
        return Fallback.badGateway();
    }
}
