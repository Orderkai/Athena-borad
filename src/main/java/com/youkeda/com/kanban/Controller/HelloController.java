package com.youkeda.com.kanban.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String HelleWorld(){
        return "HelloWorld";
    }
}
