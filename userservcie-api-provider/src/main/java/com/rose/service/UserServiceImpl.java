package com.rose.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rose.exceptions.CustomizeException;

@Service(version = "1.0.0")
//@Component
public class UserServiceImpl implements UserService {

    @Override
    @HystrixCommand(
        commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
        })
    public String talk() throws CustomizeException {
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int i = 0;
        try {
            int res = 10 % i;
        } catch (Exception ex) {
            throw new CustomizeException(ex.getMessage(), ex);
        }
        return "hello!!";
//        throw new RuntimeException("error");
    }

}
