package com.sunkang.shiro.token;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "error")
@PropertySource(value ={"classpath:tokenErrorCode.properties"},encoding="GBK")
@Component
public class TokenErrorCodeBean implements Serializable {
    private Map<String, String> codes;

    public Map<String, String> getCodes() {
        return codes;
    }

    public void setCodes(Map<String, String> codes) {
        this.codes = codes;
    }
}
