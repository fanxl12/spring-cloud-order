package com.fanxl.order.controller;

import com.fanxl.order.dto.OrderDTO;
import com.fanxl.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/4 0004 20:25
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

//    @GetMapping("/sendMessage")
//    public void process() {
//        streamClient.output().send(MessageBuilder.withPayload("hello, everyone").build());
//    }

    @GetMapping("/sendMessage")
    public void process() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("12345678");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
