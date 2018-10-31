package com.fanxl.order.client;

import com.fanxl.order.common.DecreaseStockInput;
import com.fanxl.order.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/10/26 0026 11:39
 */
@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

    @GetMapping("test/{name}")
    String getTest(@PathVariable("name") String name);


}
