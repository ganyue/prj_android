package com.mapsocial.controller;

import com.mapsocial.vo.Menu;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yue.gan on 2017/10/16.
 */
@RestController
@RequestMapping("/admins")
public class AdminController {

    /**
     * 获取后台用户管理主页
     * @param model
     * @return
     */
    public ModelAndView listUsers (Model model) {
        List<Menu> list = new ArrayList<>();
        list.add(new Menu("用户管理", "/users"));
        model.addAttribute("list", list);
        return new ModelAndView("/admins/index", "model", model);
    }
}
