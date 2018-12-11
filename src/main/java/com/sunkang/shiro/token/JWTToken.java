package com.sunkang.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * jwt的token集成shiro的token
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
