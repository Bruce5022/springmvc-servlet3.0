package com.sky.springmvc.utils;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DeferredResultQueue {

    private static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedDeque<>();

    public static void save(DeferredResult<Object> result){
        queue.add(result);
    }

    public static DeferredResult<Object> get(){
       return queue.poll();
    }
}
