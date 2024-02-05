package com.geekcloud.kanborad.service;

import com.geekcloud.kanborad.model.Result;
import com.geekcloud.kanborad.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    Result<User> login(String userName, String passWord);

    Result<User> register(String userName, String passWord);

    User findByUserName(String userName);

    List<User> findByIds(List<Long> ids);

    List<User> search(String keyWord, LocalDateTime startTime, LocalDateTime endTime);
}
