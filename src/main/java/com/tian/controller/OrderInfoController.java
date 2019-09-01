package com.tian.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sun.net.httpserver.Authenticator;
import com.tian.common.other.ResponseData;
import com.tian.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/13 0013.
 */
@RestController
@RequestMapping("orderInfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    public ResponseData commonFallback(){
        return ResponseData.successData.setData("服务忙, 请稍后重试.");
    }

    @RequestMapping("testHystrix1")
    @HystrixCommand(fallbackMethod = "commonFallback")
    public ResponseData testHystrix1(){
        String threadName = Thread.currentThread().getName();
        return ResponseData.successData.setData(threadName);
    }

    @HystrixCommand(fallbackMethod = "commonFallback")
    @RequestMapping("testHystrix2")
    public ResponseData testHystrix2(){
        String threadName = Thread.currentThread().getName();
        return ResponseData.successData.setData(threadName);
    }

    @RequestMapping("testTransaction")
    public ResponseData testTransaction(){
        orderInfoService.testTransaction();
        return ResponseData.successData;
    }


}
