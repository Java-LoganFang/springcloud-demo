package cn.com.itoken.service.sso.service.impl;

import cn.com.itoken.common.service.domain.TbSysUser;
import cn.com.itoken.service.sso.mapper.TbSysUserMapper;
import cn.com.itoken.service.sso.service.LoginService;
import cn.com.itoken.service.sso.service.consumer.RedisService;
import cn.com.itoken.common.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Override
    public TbSysUser login(String loginCode, String plantPassword) {
        TbSysUser tbSysUser = null;

        String json = redisService.get(loginCode);

        //用户信息不存在redis中
        if(json.equals("not_ok")){
            Example example = new Example(TbSysUser.class);
            example.createCriteria().andEqualTo("loginCode", loginCode);

            tbSysUser = tbSysUserMapper.selectOneByExample(example);
            if(tbSysUser == null) return null;  //不存在此帐号
            String checkPassword = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
            if(checkPassword.equals(tbSysUser.getPassword())){  //检查密码
                try {
                    redisService.put(loginCode, MapperUtils.obj2json(tbSysUser), 60 * 60 * 24);  //成功登录将数据存入redis，保存1天
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return tbSysUser;
            }
            else {  //密码错误返回空
                return null;
            }
        }
        //redis中存在
        else {
            try {
                tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);  //取出数据并返回
                return tbSysUser;
            } catch (Exception e) {
                logger.warn("触发熔断：{}", e.getMessage());
            }
        }

        return null; //意外错误
    }
}
