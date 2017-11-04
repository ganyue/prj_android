package com.mapsocial.controller;

import com.mapsocial.constant.JwtConstants;
import com.mapsocial.domain.Authority;
import com.mapsocial.domain.User;
import com.mapsocial.service.UserService;
import com.mapsocial.util.ConstraintViolationExceptionHandler;
import com.mapsocial.util.JwtUtils;
import com.mapsocial.vo.Response;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yue.gan on 2017/10/16.
 */
@Controller()
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.createUserAuthority());
        user.setAuthorities(authorities);
        user.setRtime(System.currentTimeMillis());
        user.setLtime(user.getRtime());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (StringUtils.isEmpty(user.getNick())) {
            user.setNick(user.getUsername());
        }

        try {
            userService.registerUser(user);
            return ResponseEntity.ok().body(new Response(true,
                    "register success", new User(user.getUsername(),
                    "", user.getNick(), "", "", "",
                    user.getRtime(), user.getLtime())));
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body(new Response(false,
                    ConstraintViolationExceptionHandler.getMessage(e), user));
        }
    }

    /**
     * 只有认证后才能修改用户信息，如果修改用户信息，需要比对用户名是否和token中一致
     * 这个接口需要认证后才能使用，所以一定会有token在request的header中
     */
    //TODO 不能放任修改，应该只提供用户昵称、密码、手机号、邮箱等独立的修改
    @PostMapping("/update_user")
    public ResponseEntity updateUserInfo(@RequestBody User user,
                                         @RequestParam(JwtConstants.TOKEN_HEADER_PARAM_NAME)String token) {
        String username = JwtUtils.parseToken(token).getBody().getSubject();
        if (!StringUtils.equals(user.getUsername(), username)) {
            return ResponseEntity.badRequest().body(new Response(false,"fail", user));
        }

        List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.createUserAuthority());
        user.setAuthorities(authorities);
        user.setLtime(System.currentTimeMillis());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (StringUtils.isEmpty(user.getNick())) {
            user.setNick(user.getUsername());
        }

        try {
            userService.saveOrUpdateUser(user);
            return ResponseEntity.ok().body(new Response(true,
                    "success", new User(user.getUsername(),
                    "", user.getNick(), "", "", "",
                    user.getRtime(), user.getLtime())));
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body(new Response(false,
                    ConstraintViolationExceptionHandler.getMessage(e), user));
        }
    }

//    @GetMapping("/test")
//    public ResponseEntity testGet (@RequestHeader(JwtConstants.TOKEN_HEADER_PARAM_NAME)String token) {
//        Jws<Claims> pasedToken = JwtUtils.parseToken(token);
//        return ResponseEntity.ok().body(new Response(true, "success", "testGet Method in MainController"));
//    }

    @GetMapping("/test")
    public ResponseEntity testGet () {
        return ResponseEntity.ok().body(new Response(true, "success", "testGet Method in MainController"));
    }
}
