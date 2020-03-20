package cn.com.itoken.service.sso.service;

import cn.com.itoken.common.service.domain.TbSysUser;

/**
 * 登录业务sso
 */
public interface LoginService {

    /**
     * 登录
     * @param loginCode
     * @param plantPassword 明文密码
     * @return
     */
    public TbSysUser login(String loginCode, String plantPassword);
}
