package com.sunkang.controller;

import com.sunkang.common.Result;
import com.sunkang.shiro.token.JWTToken;
import com.sunkang.shiro.token.JWTUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

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
    public Result login(String username, String password, String code) {


        String token = JWTUtils.createJwtToken(username, password, code);
        Subject subject = SecurityUtils.getSubject();
        subject.login(new JWTToken(token));

        Result result = new Result();
        result.setCode(200);
        result.setResultData(token);
        return result;
    }

}