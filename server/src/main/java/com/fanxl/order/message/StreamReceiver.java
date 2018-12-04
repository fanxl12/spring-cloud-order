package com.fanxl.order.message;

import com.fanxl.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/4 0004 20:22
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

//    @StreamListener(StreamClient.OUTPUT)
//    public void process(Object message) {
//        log.info("StreamReceiver: {}", message);
//    }

    @StreamListener(StreamClient.OUTPUT)
    @SendTo(StreamClient.INPUT)
    public String process(OrderDTO message) {
        log.info("StreamReceiver: {}", message);
        return "ok, I get it";
    }

    @StreamListener(StreamClient.INPUT)
    public void processInput(String message) {
        log.info("processInput: {}", message);
    }
}
