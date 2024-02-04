package com.geekcloud.kanborad.service;

import com.geekcloud.kanborad.model.Result;

public interface UserService {
    Result login(String userName, String passWord);

    Result register();
}
