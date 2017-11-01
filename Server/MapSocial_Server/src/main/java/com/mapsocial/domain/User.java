package com.mapsocial.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yue.gan on 2017/10/15.
 */
@Entity
public class User implements Serializable{
    private static final long serialVersionUID = User.class.getName().hashCode();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "账号不能为空")
    @Size(min = 6, max = 20, message = "账号长度需要2到20位")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "账号中不能包含特殊字符")
    @Column(nullable = false, length = 20, unique = true)
    private String username;    // 用户名

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度需要6到20位")
    @Column(nullable = false, length = 20)
    private String password;    // 密码

    @NotEmpty(message = "昵称不能为空")
    @Size(min = 2, max = 20, message = "昵称长度不合法")
    @Column(nullable = false, length = 20, unique = true)
    private String nick;        // 昵称

    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号格式非法")
    @Column(nullable = true, length = 11, unique = true)
    private String phone;       // 手机号

    @Size(max=32)
    @Email(message= "邮箱格式不对" )
    @Column(nullable = true, length = 32, unique = true)
    private String email;       // 邮箱

    @Column(length = 128)
    private String portrait;    //头像

    private Long rtime;         // 注册时间
    private Long ltime;         // 最近登录时间

    public User() {
    }

    public User(String username, String password, String nick,
                String phone, String email, String portrait, Long rtime, Long ltime) {
        this.username = username;
        this.password = password;
        this.nick = nick;
        this.phone = phone;
        this.email = email;
        this.portrait = portrait;
        this.rtime = rtime;
        this.ltime = ltime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nick='" + nick + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", portrait='" + portrait + '\'' +
                ", rtime=" + rtime +
                ", ltime=" + ltime +
                '}' + "\n" + serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Long getRtime() {
        return rtime;
    }

    public void setRtime(Long rtime) {
        this.rtime = rtime;
    }

    public Long getLtime() {
        return ltime;
    }

    public void setLtime(Long ltime) {
        this.ltime = ltime;
    }
}
