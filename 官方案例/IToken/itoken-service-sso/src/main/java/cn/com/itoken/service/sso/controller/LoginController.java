package cn.com.itoken.service.sso.controller;

import cn.com.itoken.common.service.domain.TbSysUser;
import cn.com.itoken.service.sso.service.LoginService;
import cn.com.itoken.service.sso.service.consumer.RedisService;
import cn.com.itoken.common.utils.CookieUtils;
import cn.com.itoken.common.utils.MapperUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String url,
                        HttpServletRequest request, Model model){
        String token = CookieUtils.getCookieValue(request, "token");
        //token不为空，可能已登录
        if(StringUtils.isNotBlank(token)){
            String loginCode = redisService.get(token);
            if(StringUtils.isNotBlank(loginCode)){
                String json = redisService.get(loginCode);
                if(StringUtils.isNotBlank(json)){
                    try {
                        TbSysUser tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                        if(tbSysUser != null){
                            //如果存在url，重定向到url页面
                            if(StringUtils.isNotBlank(url)){
                                return "redirect:" + url;
                            }
                        }
                        //没有url，将登录信息返回前端
                        model.addAttribute("tbSysUser", tbSysUser);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if(StringUtils.isNotBlank(url)){
            model.addAttribute("url", url);
        }

        return "login";
    }

    /**
     * 登录业务
     * @param loginCode
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String loginCode, String password, @RequestParam(required = false) String url,
                        HttpServletRequest request, HttpServletResponse response,
                        RedirectAttributes redirectAttributes){

        TbSysUser tbSysUser = loginService.login(loginCode, password);

        if(tbSysUser == null) {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误，请重新输入");
        }
        else {
            String token = UUID.randomUUID().toString();
            String result = redisService.put(token, loginCode, 60 * 60 * 24);
            if(result.equals("ok")){
                CookieUtils.setCookie(request, response, "token", token, 60 * 60 * 24);  //token存入Cookie
                if(StringUtils.isNotBlank(url)){
                    return "redirect:" + url;  //重定向页面
                }
            }

            //熔断处理
            else {
                redirectAttributes.addFlashAttribute("message", "服务器异常，请稍后再试");
            }
        }
        return "redirect:/login"; //重定向至login页面，使用上方login方法
    }

    /**
     * 注销
     * @param request
     * @param response
     * @param url
     * @param model
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(required = false) String url,  Model model){
        try {
            CookieUtils.deleteCookie(request, response, "token");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return login(url, request, model);
    }
}
