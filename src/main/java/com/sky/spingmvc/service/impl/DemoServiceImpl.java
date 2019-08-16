package com.sky.spingmvc.service.impl;

import com.sky.spingmvc.service.DemoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DemoServiceImpl implements DemoService {

    public Map<String, String> getContent(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "0");
        map.put("name", name);
        return map;
    }
}
