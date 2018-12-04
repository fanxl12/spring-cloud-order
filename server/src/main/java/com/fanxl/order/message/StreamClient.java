package com.fanxl.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/4 0004 20:20
 */
public interface StreamClient {

    String INPUT = "inputMessage";

    String OUTPUT = "outPutMessage";

    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.OUTPUT)
    MessageChannel output();

}
