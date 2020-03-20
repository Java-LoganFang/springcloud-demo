package cn.com.itoken.web.admin.interceptor;

import cn.com.itoken.common.service.domain.TbSysUser;
import cn.com.itoken.web.admin.service.RedisService;
import cn.com.itoken.common.utils.CookieUtils;
import cn.com.itoken.common.utils.MapperUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class WebAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        //一定没有登录
        if(StringUtils.isBlank(token)){
            try {
                response.sendRedirect("http://localhost:8503/login?url=http://localhost:8601");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        TbSysUser tbSysUser = (TbSysUser) session.getAttribute("tbSysUser");

        //已登录
        if(tbSysUser != null){
            if(modelAndView != null){
                modelAndView.addObject("tbSysUser", tbSysUser);
            }
        }

        //未登录
        else {
            String token = CookieUtils.getCookieValue(request, "token");
            if(StringUtils.isNotBlank(token)){
                String loginCode = redisService.get(token);
                if(StringUtils.isNotBlank(loginCode)){
                    String json = redisService.get(loginCode);
                    if(!json.equals("not_ok")){
                        try {
                            //已登录，创建局部会话
                            tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                            if(modelAndView != null){
                                modelAndView.addObject("tbSysUser", tbSysUser);
                            }
                            session.setAttribute("tbSysUser", tbSysUser);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        //二次确认是否存在登录信息，不存在则要求用户重新登录
        if(tbSysUser == null){
            response.sendRedirect("http://localhost:8503/login?url=http://localhost:8601");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
