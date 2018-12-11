package com.sunkang.shiro.token;

/**
 * token字符串组成部分
 */
public class AcessToken {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
     private String  password;

    /**
     * 类型
     */
    private String tokenType;


    /**
     * token有效期
     */
    private long expiresIn;

    public AcessToken() {
    }

    public AcessToken(String username, String password, String tokenType, long expiresIn) {
        this.username = username;
        this.password = password;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "AcessToken{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
