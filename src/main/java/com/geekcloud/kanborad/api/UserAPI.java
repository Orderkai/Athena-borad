package com.geekcloud.kanborad.api;

import com.geekcloud.kanborad.model.User;
import com.geekcloud.kanborad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserAPI {

    @Autowired
    private UserService userService;

    @GetMapping("/api/user/search")
    @ResponseBody
    public List<User> search(@RequestParam("keyWord") String keyWord,
                             @RequestParam(value = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                             LocalDateTime startTime,
                             @RequestParam(value = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                 LocalDateTime endTime) {

        return userService.search(keyWord, startTime, endTime);
    }
}
