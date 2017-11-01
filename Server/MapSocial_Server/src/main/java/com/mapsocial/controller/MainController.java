package com.mapsocial.controller;

import com.mapsocial.domain.User;
import com.mapsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yue.gan on 2017/10/16.
 */
@Controller
public class MainController {

    private static final Integer ROL_USER_AUTHORITY_ID = 2;

    @Autowired
    private UserService userService;
//
//    @GetMapping("/")
//    public String root() {
//        return "redirect:/index";
//    }
//
//    @GetMapping("/index")
//    public String index () {
//        return "index";
//    }
//
//    @GetMapping("/login")
//    public String login () {
//        return "login";
//    }
//
//    @GetMapping("/login-error")
//    public String loginError (Model model) {
//        model.addAttribute("loginError", true);
//        model.addAttribute("errorMsg", "登录失败，账号或者密码错误");
//        return "login";
//    }
//
//    @GetMapping("/register")
//    public String register () {
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(User user) {
//        userService.registerUser(user);
//        return "redirect:/login";
//    }
}
