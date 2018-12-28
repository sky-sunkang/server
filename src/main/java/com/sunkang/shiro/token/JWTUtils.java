package com.sunkang.shiro.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;


/**
 * token 的处理类
 */
public class JWTUtils {
    private static String  SECRET = "SUNKANGHH";

    /**
     * 生成token
     * @return
     */
    public static String createJwtToken(String username,String password,String tokenType){
        // expire time
        Calendar nowTime = Calendar.getInstance();
        //有30分钟有效期
        nowTime.add(Calendar.MILLISECOND, 30*60*1000);
        Date expiresDate = nowTime.getTime();
        Claims claims = Jwts.claims();
        claims.put("username",username);
        claims.put("password", password);
        claims.put("tokenType", tokenType);
        claims.put("expiresIn", expiresDate);
        claims.setAudience("auth0");//观众
        claims.setIssuer("sunkang");//发行者
        String token = Jwts.builder().setClaims(claims).setExpiration(expiresDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static AcessToken parseJwtToken(String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        String signature = jws.getSignature();
        Map<String, String> header = jws.getHeader();
        Claims caims = jws.getBody();
        return new AcessToken(caims.get("username")+"",caims.get("password")+"",caims.get("tokenType")+"",Long.parseLong(caims.get("expiresIn")+""));
    }

    public static void main(String[] args) {
        System.out.println(createJwtToken("kang","kang","kang"));
    }
}
