package com.rose.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rose.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    //version必须
    @Reference(version = "1.0.0")
    @Lazy
    private UserService userService;

    @HystrixCommand(fallbackMethod = "fallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
            })
    @RequestMapping("/user")
    public String talk() {

        return userService.talk();
    }

    public String fallback(Throwable error) {
        error.printStackTrace();
        return "Hystrix fallback";
    }

}
