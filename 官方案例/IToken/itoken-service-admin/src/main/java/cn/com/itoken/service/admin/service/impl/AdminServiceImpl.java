package cn.com.itoken.service.admin.service.impl;

import cn.com.itoken.common.service.BaseService.Impl.BaseServiceImpl;
import cn.com.itoken.common.service.domain.TbSysUser;
import cn.com.itoken.common.service.mapper.TbSysUserMapper;
import cn.com.itoken.service.admin.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements AdminService<TbSysUser> {

}
