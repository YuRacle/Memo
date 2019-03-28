package com.feliz.memo.controller;

import com.feliz.memo.bean.User;
import com.feliz.memo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/signIn")
    @ResponseBody
    public int signIn(User user, HttpSession session) {
        User _user = userService.signIn(user);
        if (_user != null) {
            //保存登录状态
            session.setAttribute("loginUser", _user.getId());
            return 1;
        }else {
            return 0;
        }
    }

    @RequestMapping("/quit")
    public String  signOut(HttpSession session) {
        session.setAttribute("loginUser",null);
        return "redirect:/login";
    }

    @RequestMapping("/signUp")
    @ResponseBody
    public int signUp(User user) {
        return userService.signUp(user);
    }

}
