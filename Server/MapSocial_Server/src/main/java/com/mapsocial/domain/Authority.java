package com.mapsocial.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by yue.gan on 2017/10/24.
 */

@Entity
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = Authority.class.getName().hashCode();

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
