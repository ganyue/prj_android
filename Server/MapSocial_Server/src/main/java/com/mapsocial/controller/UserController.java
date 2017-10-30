package com.mapsocial.controller;

import com.mapsocial.domain.Authority;
import com.mapsocial.domain.User;
import com.mapsocial.service.AuthorityService;
import com.mapsocial.service.AuthorityServiceImpl;
import com.mapsocial.service.UserService;
import com.mapsocial.util.ConstraintViolationExceptionHandler;
import com.mapsocial.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yue.gan on 2017/10/16.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    private final String userModelName = "userModel";

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;


    /**
     * 根据昵称分页查询用户
     * @param pageIndex
     * @param pageSize
     * @param nick
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(@RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "nick", required = false, defaultValue = "") String nick,
                             Model model) {
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<User> page = userService.listUserByNickLike(nick, pageable);
        List<User> list = page.getContent();

        model.addAttribute("page", page);
        model.addAttribute("userList", list);
        return new ModelAndView("users/list", "userModel", model);
    }

    @GetMapping("/add")
    public ModelAndView createForm (Model model) {
        model.addAttribute("user", new User());
        return new ModelAndView("users/add", "userModel", model);
    }

    @PostMapping
    public ResponseEntity<Response> saveOrUpdateUser (User user, Integer authorityId) {
        try {
            List<Authority> authorities = new ArrayList<>();
            authorities.add(authorityService.getAuthorityById(authorityId));
            user.setAuthorities(authorities);
            userService.saveOrUpdateUser(user);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false,
                    ConstraintViolationExceptionHandler.getMessage(e), user));
        }
        return ResponseEntity.ok().body(new Response(true, "success", user));
    }

    @GetMapping(value = "delete/{id}")
//    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Response> delete (@PathVariable("id") Integer id) {
        try {
            userService.removeUser(id);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false,
                    ConstraintViolationExceptionHandler.getMessage(e), id));
        }
        return ResponseEntity.ok().body(new Response(true, "success", id));
    }

    @GetMapping(value = "edit/{id}")
    public ModelAndView modifyForm (@PathVariable("id")Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return new ModelAndView("users/edit", "userModel", model);
    }
}
