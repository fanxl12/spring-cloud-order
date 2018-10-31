package com.fanxl.order;

import com.fanxl.order.client.ProductClient;
import com.fanxl.order.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/10/26 0026 11:31
 */
@Slf4j
@RequestMapping("/test")
@RestController
public class TestRestController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @GetMapping("one")
    public String test1() {
        // 1.第一种方式
        RestTemplate restTemplate1 = new RestTemplate();
        String result = restTemplate1.getForObject("http://localhost:8600/test/fanxl1", String.class);
        log.info("访问返回的结果1:{}", result);
        return result;
    }

    @GetMapping("two")
    public String test2() {
        // 2.第二种方式
        RestTemplate restTemplate2 = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s/test/fanxl2", serviceInstance.getHost(), serviceInstance.getPort());
        String result = restTemplate2.getForObject(url, String.class);
        log.info("访问返回的结果2:{}", result);
        return result;
    }

    @GetMapping("three")
    public String test3() {
        // 3.第三种方式
        String result = restTemplate.getForObject("http://PRODUCT/test/fanxl3", String.class);
        log.info("访问返回的结果3:{}", result);
        return result;
    }

    @GetMapping("four")
    public String test4() {
        // 4.第四种方式
        String result = productClient.getTest("fanxl4");
        log.info("访问返回的结果4:{}", result);
        return result;
    }

    @GetMapping("getProductList")
    public String getProductList() {
        List<ProductInfoOutput> productList = productClient.listForOrder(Arrays.asList("164103465734242707"));
        log.info("访问返回的结果5:{}", productList.size());
        return "查询成功";
    }


}
