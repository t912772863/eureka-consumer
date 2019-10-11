package com.tian.stream;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * Created by z on 2019/9/18.
 */
@Component
public class Consumer {
    @StreamListener("my_stream_channel")
    public void redMsg(String msg){
        System.out.println("消费者收到消息: "+msg);
    }

}
