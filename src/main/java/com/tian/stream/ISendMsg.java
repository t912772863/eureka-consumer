package com.tian.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * 创建发送消息通道
 * Created by z on 2019/9/18.
 */
public interface ISendMsg {
    /**
     * 思考, rabbit有交换机, 队列
     * 默认以通道名称, 创建交换机,
     *
     *
     * @return
     */
    @Output("my_stream_channel")
    SubscribableChannel sendMsg();
}
