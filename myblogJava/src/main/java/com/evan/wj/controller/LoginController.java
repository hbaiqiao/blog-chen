package com.evan.wj.controller;

import com.evan.wj.pojo.User;
import com.evan.wj.result.Result;
import com.evan.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
   // @PostMapping(value = "/api/login")
    @PostMapping("/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session) {
        String num = requestUser.getNum();
        num = HtmlUtils.htmlEscape(num);

        User user = userService.get(num, requestUser.getPassword());
        if (null == user) {
            System.out.println("登录失败");
            return new Result(400);
        } else {
            session.setAttribute("user", user);
            System.out.println("登录成功");
            return new Result(200);
        }
    }
}

