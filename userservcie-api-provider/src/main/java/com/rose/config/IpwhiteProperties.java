package com.rose.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ipwhite.properties")
public class IpwhiteProperties {

    private String ipwhite;

    public String getIpwhile() {
        return ipwhite;
    }

    public void setIpwhile(String ipwhite) {
        this.ipwhite = ipwhite;
    }
}
