package com.geekcloud.kanborad.dao;

import com.geekcloud.kanborad.dataobject.UserDO;
import com.geekcloud.kanborad.model.Result;
import com.geekcloud.kanborad.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserDAO {

    int add(UserDO userDO);

    int update(UserDO userDO);

    int delete(@Param("id")long id);

    UserDO findByUserName(String userName);

    List<UserDO> findByIds(@Param("ids") List<Long> ids);

    List<UserDO> query(@Param("userName") String name);

    List<UserDO> search(@Param("keyWord") String keyWord, @Param("startTime") LocalDateTime startTime,
                        @Param("endTime") LocalDateTime endTime);

}
