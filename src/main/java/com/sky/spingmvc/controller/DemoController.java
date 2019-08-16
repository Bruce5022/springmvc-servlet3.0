package com.sky.spingmvc.controller;

import com.sky.spingmvc.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/{name}")
    public String demo(Model model, @PathVariable("name") String name) {
        return demoService.getContent(name);
    }
}
