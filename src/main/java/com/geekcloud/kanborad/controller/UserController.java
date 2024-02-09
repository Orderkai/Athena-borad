package com.geekcloud.kanborad.controller;

import com.geekcloud.kanborad.dao.UserDAO;
import com.geekcloud.kanborad.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/user")
    @ResponseBody
    public UserDO save(@RequestBody UserDO userDO){
        userDAO.add(userDO);
        return userDO;
    }
}
