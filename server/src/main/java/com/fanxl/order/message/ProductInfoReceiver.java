package com.fanxl.order.message;

import com.fanxl.order.utils.JsonTool;
import com.fanxl.product.common.ProductInfoOutput;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/4 0004 23:04
 */
@Slf4j
@Component
public class ProductInfoReceiver {

    public static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        log.info("productInfoOutput-message:{}", message);
        List<ProductInfoOutput> productInfoOutputList = JsonTool.fromJson(new TypeReference<List<ProductInfoOutput>>() {}, message);
        log.info("productInfoOutput:{}", productInfoOutputList);
        productInfoOutputList.forEach(p -> {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, p.getProductId()),
                    String.valueOf(p.getProductStock()));
        });
    }

}
