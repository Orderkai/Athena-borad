package com.geekcloud.kanborad.controller;

import com.geekcloud.kanborad.dao.TaskDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TaskController {

    @Autowired
    private TaskDAO taskDAO;
}
