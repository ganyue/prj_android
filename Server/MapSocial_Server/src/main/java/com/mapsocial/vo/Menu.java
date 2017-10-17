package com.mapsocial.vo;

/**
 * 后台管理菜单
 * Created by yue.gan on 2017/10/16.
 */
public class Menu {
    private String name;    //菜单名称
    private String url;     //菜单url

    public Menu() {
    }

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
