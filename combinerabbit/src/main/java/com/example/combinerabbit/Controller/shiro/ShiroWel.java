package com.example.combinerabbit.Controller.shiro;

import com.example.combinerabbit.Service.ShiroServiceImpl;
import com.example.combinerabbit.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShiroWel {

    @Autowired
    ShiroServiceImpl shiroService;

    @GetMapping("/shirowel")
    public String val() {

        return "views/wel";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(username + "---" + password);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            System.out.println("登录失败-----------");
        }
        return "forward:/";
    }


    @RequestMapping("/wells")
    @ResponseBody
    public String wells() {
        User ok = shiroService.findUserByUserName("ok");
        System.out.println(ok + "+++++++++++++++++++++");
        return "ok";
    }

    @RequestMapping("/home")
    @ResponseBody
    public String home() {
        System.out.println("+++++++++++++++++++++");
        return "ok";
    }
}
