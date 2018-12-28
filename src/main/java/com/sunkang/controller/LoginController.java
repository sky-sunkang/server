package com.sunkang.controller;

import com.sunkang.common.Result;
import com.sunkang.service.RedisService;
import com.sunkang.shiro.token.JWTToken;
import com.sunkang.shiro.token.JWTUtils;
import com.sunkang.shiro.token.TokenErrorCodeBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private TokenErrorCodeBean tokenErrorCodeBean;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping("login")
    public Result login( String username, String password, String code) {

        try{

            String token = JWTUtils.createJwtToken(username, password, code);
            Subject subject = SecurityUtils.getSubject();
            subject.login(new JWTToken(token));

            Result result = new Result();
            result.setCode(200);
            result.setCodeDec("ok");
            result.setResultData(token);
            //放入缓存 半个小时
            redisService.set("token",token, 30l*60*1000l);
            return result;
        }catch (Exception e){
            Result result = new Result();
            result.setCode(Integer.parseInt(e.getMessage()) );
            String errorMessage=tokenErrorCodeBean.getCodes().get(e.getMessage());
            result.setCodeDec(errorMessage);
            result.setResultData(e.getClass());
            return result;
        }
    }

}