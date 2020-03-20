package cn.com.itoken.service.admin;

import cn.com.itoken.common.service.domain.TbSysUser;
import cn.com.itoken.service.admin.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItokenServiceAdminApplication.class)
@Transactional
@Rollback
public class ItokenServiceAdminApplicationTests {

    @Autowired
    private AdminService adminService;

    @Test
    public void register() {
        TbSysUser tbSysUser = new TbSysUser();
        tbSysUser.setUserCode(UUID.randomUUID().toString());
        tbSysUser.setLoginCode("admin@test.com");
        tbSysUser.setPassword("123456");
        tbSysUser.setUserName("admin");
        tbSysUser.setUserType("0");
        tbSysUser.setMgrType("1");
        tbSysUser.setStatus("0");
        tbSysUser.setCreateDate(new Date());
        tbSysUser.setCreateBy(tbSysUser.getUserCode());
        tbSysUser.setUpdateDate(new Date());
        tbSysUser.setUpdateBy(tbSysUser.getUserCode());
        tbSysUser.setCorpCode("0");
        tbSysUser.setCorpName("Admin");

        adminService.register(tbSysUser);
    }

    @Test
    public void login() {
        TbSysUser tbSysUser =  adminService.login("admin@test.com", "123456");
        Assert.assertNotNull(tbSysUser);
    }


}
