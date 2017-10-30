package com.mapsocial.service;

import com.mapsocial.domain.Authority;
import com.mapsocial.domain.AuthorityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yue.gan on 2017/10/24.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public Authority getAuthorityById(Integer id) {
        return authorityDao.findOne(id);
    }
}
