package com.mapsocial.service;

import com.mapsocial.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by yue.gan on 2017/10/16.
 */
public interface UserService {

    User saveOrUpdateUser (User user);                              // 更新
    User registerUser (User user);                                  // 注册/新增
    void removeUser (Integer id);                                   // 删除
    User getUserById (Integer id);                                  // 根据id查找
    Page<User> listUserByNickLike (String nick, Pageable pageable); // 根据昵称模糊查询
}
