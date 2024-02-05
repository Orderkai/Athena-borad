package com.geekcloud.kanborad.service.impl;

import com.geekcloud.kanborad.dao.UserDAO;
import com.geekcloud.kanborad.model.User;
import com.geekcloud.kanborad.service.UserService;
import com.geekcloud.kanborad.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Result<User> login(String userName, String passWord) {
        return null;
    }

    @Override
    public Result<User> register(String userName, String passWord) {
        return null;
    }

    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public List<User> findByIds(List<Long> ids) {
        return null;
    }

    @Override
    public List<User> search(String keyWord, LocalDateTime startTime, LocalDateTime endTime) {
        return null;
    }
}
