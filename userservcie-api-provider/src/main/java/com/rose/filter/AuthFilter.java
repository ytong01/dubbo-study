package com.rose.filter;


import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.rose.config.IpwhiteProperties;

import javax.annotation.Resource;

@Activate(group = Constants.PROVIDER)
public class AuthFilter implements Filter {

    @Resource
    private IpwhiteProperties properties;

    public void setProperties(IpwhiteProperties properties) {
        this.properties = properties;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }
}
