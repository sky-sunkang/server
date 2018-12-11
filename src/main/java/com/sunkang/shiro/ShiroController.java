package com.sunkang.shiro;

import com.sunkang.common.Result;
import com.sunkang.shiro.token.TokenErrorCodeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Map;

/**
 *  shiro鉴权错误处理
 */
@Controller
public class ShiroController {

    @Autowired
    private TokenErrorCodeBean tokenErrorCodeBean;


    @ResponseBody
    @RequestMapping("shiroError")
    public Result login(ServletRequest req, ServletResponse resp) {
        String errorClass=(String)req.getAttribute("errorClass");
        String errorCode=(String)req.getAttribute("errorCode");
        Result result = new Result();
        result.setCode(Integer.parseInt(errorCode) );
        String errorMessage=tokenErrorCodeBean.getCodes().get(errorCode);
        result.setCodeDec(errorMessage);
        result.setResultData(errorClass);
        return result;
    }
}
