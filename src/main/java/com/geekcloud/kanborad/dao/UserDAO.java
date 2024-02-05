package com.geekcloud.kanborad.dao;

import com.geekcloud.kanborad.dataobject.UserDO;
import com.geekcloud.kanborad.model.Result;
import com.geekcloud.kanborad.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserDAO {

    int add(UserDO userDO);

}