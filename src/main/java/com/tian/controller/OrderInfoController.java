package com.tian.controller;

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

    @RequestMapping("testTransaction")
    public ResponseData testTransaction(){
        orderInfoService.testTransaction();
        return ResponseData.successData;
    }


}
