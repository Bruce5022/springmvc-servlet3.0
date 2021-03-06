package com.sky.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World !";
    }

    @RequestMapping("/demo")
    public ModelAndView demo(ModelAndView modelAndView) {
        System.out.println(">>>DemoController<<<");
        modelAndView.setViewName("demo");
        return modelAndView;
    }
}
