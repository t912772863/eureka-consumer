package com.tian.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by z on 2019/9/18.
 */
public interface IReadMsg {
    @Input("my_stream_channel")
    SubscribableChannel readMsg();
}
