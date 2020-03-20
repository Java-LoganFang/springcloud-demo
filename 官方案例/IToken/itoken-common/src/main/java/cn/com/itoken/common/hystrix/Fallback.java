package cn.com.itoken.common.hystrix;

import cn.com.itoken.common.constants.HttpStatusConstants;
import cn.com.itoken.common.dto.BaseResult;
import cn.com.itoken.common.utils.MapperUtils;
import com.google.common.collect.Lists;

/**
 * 通用熔断方法
 */
public class Fallback {
    /**
     * 熔断器 502错误
     * @return
     */
    public static String badGateway(){
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(
                new BaseResult.Error
                        (String.valueOf(HttpStatusConstants.BAD_GATWAY.getStatus()),
                                HttpStatusConstants.BAD_GATWAY.getContent())));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
