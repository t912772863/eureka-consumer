package com.tian.controller;

import com.tian.stream.ISendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.UUID;

/**
 * Created by z on 2019/9/18.
 */
public class SendMsgController {

    @Autowired
    private ISendMsg sendMsg;
    /*
    生产者流程:
    1. 创建发送消息通道
    2. 生产投递消息
    3. 开启绑定
     */

    public String sendMsg(){
        String msg = UUID.randomUUID().toString();
        System.out.println("生产者发送内容msg: "+msg);
        Message build = MessageBuilder.withPayload(msg.getBytes()).build();
        sendMsg.sendMsg().send(build);
        return "OK";

    }
}
