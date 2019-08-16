package com.sky.spingmvc.service.impl;

import com.sky.spingmvc.service.DemoService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DemoServiceImpl implements DemoService {

    public String getContent(String name) {
        return "content---" + name;
    }
}
