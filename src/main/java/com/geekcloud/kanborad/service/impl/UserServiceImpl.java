package com.geekcloud.kanborad.service.impl;

import com.geekcloud.kanborad.dao.UserDAO;
import com.geekcloud.kanborad.dataobject.UserDO;
import com.geekcloud.kanborad.model.User;
import com.geekcloud.kanborad.service.UserService;
import com.geekcloud.kanborad.model.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Result<User> login(String userName, String pwd) {
        return null;
    }

     /**
     * 注册用户
     * @param userName
     * @param pwd
     * @return result
     */
    @Override
    public Result<User> register(String userName, String pwd) {
        Result<User> result = new Result<>();
        if (StringUtils.isBlank(userName)) {
           result.setCode("600");
           result.setMessage("用户名不能为空");
           return result;
        }
        if (StringUtils.isBlank(pwd)) {
            result.setCode("601");
            result.setMessage("密码不能为空");
            return result;
        }
        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO != null) {
            result.setCode("602");
            result.setMessage("用户名已经存在");
            return result;
        }
        //MD5
        String saltPwd = pwd + "_geekcloud1314";
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();

        UserDO userDO1 = new UserDO();
        userDO1.setUserName(userName);
        userDO1.setPwd(md5Pwd);

        //TODO 需要添加UserDo转User
        result.setData(new User());
        return result;
    }


}
