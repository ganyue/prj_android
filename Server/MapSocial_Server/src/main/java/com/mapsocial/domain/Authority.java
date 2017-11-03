package com.mapsocial.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by yue.gan on 2017/10/24.
 *
 * <p>事先在数据库中添加了Authority的数据；
 * <p>user的id为1，name为ROLE_USER
 * <p>admin的id为10，name为ROLE_ADMIN
 */

@Entity
public class Authority implements GrantedAuthority {

    private static final Integer AUTHORITY_USER_ID = 1;
    private static final Integer AUTHORITY_ADMIN_ID = 10;
    private static final String AUTHORITY_USER_NAME = "ROLE_USER";
    private static final String AUTHORITY_ADMIN_NAME = "ROLE_ADMIN";

    private static final long serialVersionUID = Authority.class.getName().hashCode();

    /**
     * 创建一个user的Authority对象
     * @return authority对象，id=1，name="ROLE_USER"
     */
    public static Authority createUserAuthority() {
        return new Authority(AUTHORITY_USER_ID, AUTHORITY_USER_NAME);
    }

    public Authority() {
    }

    public Authority(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
