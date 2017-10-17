package com.mapsocial.service;

import com.mapsocial.domain.User;
import com.mapsocial.domain.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by yue.gan on 2017/10/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public User saveOrUpdateUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public User registerUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public void removeUser(Integer id) {
        userDao.delete(id);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.findOne(id);
    }

    @Override
    public Page<User> listUserByNickLike(String nick, Pageable pageable) {
        nick = "%" + nick + "%";
        return userDao.findByNickLike(nick, pageable);
    }
}
