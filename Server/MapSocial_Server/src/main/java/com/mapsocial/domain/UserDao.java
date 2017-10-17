package com.mapsocial.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yue.gan on 2017/10/16.
 */
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * 根据昵称搜索用户
     * @param nick
     * @param pageable
     * @return
     */
    Page<User> findByNickLike (String nick, Pageable pageable);

    /**
     * 根据账号查询
     * @param username
     * @return
     */
    User findByUsername(String username);
}
