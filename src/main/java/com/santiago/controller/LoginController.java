package com.santiago.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping(value = "/user/login")
//    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123".equals(password)){
            //登录成功
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
//            return "dashboard";
        }else{
            //登录失败
            map.put("msg", "用户名密码错误");
            return "login";
        }
    }

}
