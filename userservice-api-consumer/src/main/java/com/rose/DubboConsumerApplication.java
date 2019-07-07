package com.rose;

import com.alibaba.boot.dubbo.actuate.autoconfigure.DubboHealthIndicatorAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication(exclude = DubboHealthIndicatorAutoConfiguration.class)
@EnableHystrix
public class DubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }
}
