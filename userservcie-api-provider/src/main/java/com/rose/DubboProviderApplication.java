package com.rose;

import com.alibaba.boot.dubbo.actuate.autoconfigure.DubboHealthIndicatorAutoConfiguration;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.rose.config.IpwhiteProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication(exclude = DubboHealthIndicatorAutoConfiguration.class)
@EnableHystrix
//必须添加，否则无法注册服务
@EnableDubbo
@EnableConfigurationProperties(IpwhiteProperties.class)
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
//        Main.main(args);
    }
}
