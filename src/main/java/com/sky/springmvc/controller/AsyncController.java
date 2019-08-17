package com.sky.springmvc.controller;

import com.sky.springmvc.utils.DeferredResultQueue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
public class AsyncController {

    // 接受并发量大的创建订单请求,异步执行创建,这里立即返回
    @RequestMapping(value = "/createOrder", produces = "application/json;charset=utf-8;")
    public DeferredResult<Object> createOrder() {
        // 创建订单耗时,主线程考虑并发量,采取异步方式处理
        DeferredResult<Object> deferredResult = new DeferredResult<Object>(10000L, "创建订单失败");
        DeferredResultQueue.save(deferredResult);
        return deferredResult;
    }


    // 手工触发创建
    @RequestMapping(value = "/manulCreate", produces = "application/json;charset=utf-8;")
    public String manulCreate() {
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        String orderId = UUID.randomUUID().toString();
        deferredResult.setResult(orderId);
        return "创建成功:单号-" + orderId;
    }


    @RequestMapping("/async01")
    public Callable<String> async01() {
        long start = System.currentTimeMillis();
        System.out.println("主线程[" + Thread.currentThread().getName() + "]- do async01 >>>");
        Callable<String> callable = () -> {
            System.out.println("副线程[" + Thread.currentThread().getName() + "]- 开始...");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("副线程[" + Thread.currentThread().getName() + "]- 结束 ! 耗时: " + ((System.currentTimeMillis() - start) / 1000.0) + "秒");
            return "AsyncController.async01()";
        };
        System.out.println("主线程[" + Thread.currentThread().getName() + "]- do async01 end ! 耗时: " + ((System.currentTimeMillis() - start) / 1000.0) + "秒");
        return callable;
    }
}
