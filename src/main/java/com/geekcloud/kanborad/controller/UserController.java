package com.geekcloud.kanborad.controller;

import com.geekcloud.kanborad.dao.UserDAO;
import com.geekcloud.kanborad.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/users")
    @ResponseBody
    public List<UserDO> findAll(){
        return userDAO.findAll();
    }
}
