package com.mapsocial.dbbeans;

import org.hibernate.validator.constraints.Email;

/**
 * Created by yue.gan on 2017/10/15.
 */
public class User {
    private Integer id;
    @Email
    private String phone;
    private String password;
    private String nick;
}
