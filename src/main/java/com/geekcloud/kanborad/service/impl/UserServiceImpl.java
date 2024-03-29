package com.geekcloud.kanborad.service.impl;

import com.geekcloud.kanborad.dao.UserDAO;
import com.geekcloud.kanborad.dataobject.UserDO;
import com.geekcloud.kanborad.model.User;
import com.geekcloud.kanborad.service.UserService;
import com.geekcloud.kanborad.model.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserDO userDO;

    @Override
    public Result<User> register(String userName, String pwd) {
        Result<User> result = new Result<>();

        if (StringUtils.isEmpty(userName)) {
            result.setCode("600");
            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(pwd)) {
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

        // 密码加自定义盐值，确保密码安全
        String saltPwd = pwd + "_gc1314";
        // 生成md5值，并转为大写字母
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();

        UserDO userDO1 = new UserDO();
        userDO1.setUserName(userName);
        // 初始昵称等于用户名
        userDO1.setNickName(userName);
        userDO1.setPwd(md5Pwd);

        userDAO.add(userDO1);

        result.setSuccess(true);

        result.setData(userDO1.toModel());

        return result;
    }

    @Override
    public Result<User> login(String userName, String pwd) {

        Result<User> result = new Result<>();

        if (StringUtils.isEmpty(userName)) {
            result.setCode("600");
            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtils.isEmpty(pwd)) {
            result.setCode("601");
            result.setMessage("密码不能为空");
            return result;
        }

        UserDO userDO = userDAO.findByUserName(userName);
        if (userDO == null) {
            result.setCode("602");
            result.setMessage("用户名不存在");
            return result;
        }

        // 密码加自定义盐值，确保密码安全
        String saltPwd = pwd + "_gc1314";
        // 生成md5值，并转为大写字母
        String md5Pwd = DigestUtils.md5Hex(saltPwd).toUpperCase();

        if (!StringUtils.equals(md5Pwd, userDO.getPwd())) {
            result.setCode("603");
            result.setMessage("密码不对");
            return result;
        }

        result.setSuccess(true);

        result.setData(userDO.toModel());

        return result;
    }

    /**
     * 搜索别人
     * @param userName
     * @return
     */
    @Override
    public User findByUserName(String userName) {
        // 防止输入空参数的异常情况
        if (StringUtils.isBlank(userName)) {
            return null;
        }

        UserDO userDO = userDAO.findByUserName(userName);
        return userDO.toModel();
    }

    @Override
    public List<User> findByIds(List<Long> ids) {
        //防止不输入参数的异常情况
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

        List<UserDO> userDOs = userDAO.findByIds(ids);
        return toModels(userDOs);
    }

    @Override
    public List<User> search(String keyWord, LocalDateTime startTime, LocalDateTime endTime) {
        // 防止输入全为空的异常情况
        if (StringUtils.isBlank(keyWord) && startTime == null && endTime == null) {
            return Collections.emptyList();
        }

        List<UserDO> userDOs = userDAO.search(keyWord, startTime, endTime);
        return toModels(userDOs);
    }

    private List<User> toModels(List<UserDO> userDOs) {
        List<User> users = new ArrayList<>();

        if (!CollectionUtils.isEmpty(userDOs)) {
            for (UserDO userDO : userDOs) {
                users.add(userDO.toModel());
            }
        }

        return users;
    }


}
