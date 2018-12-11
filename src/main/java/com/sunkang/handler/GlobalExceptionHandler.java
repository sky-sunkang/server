//package com.sunkang.handler;
//
//import com.sunkang.common.Result;
//import org.apache.shiro.authc.AuthenticationException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import java.security.SignatureException;
//
///**
// * springboot的错误的统一处理
// */
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ResponseBody
//    @ExceptionHandler(value = Exception.class)
//    public Result<String> defaultErrorHandler(HttpServletRequest req, Exception e) {
//        Result<String> result=new Result();
//        result.setCode(1001);
//        result.setCodeDec(e.getMessage());
//        result.setResultData(null);
//        return result;
//    }
//
//    @ExceptionHandler(SignatureException.class)
//    @ResponseBody
//    public String defaultExceptionHandler(HttpServletRequest req,Exception e){
//
//
//        return "对不起，你没有访问权限！";
//    }
//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseBody
//    public String AuthenticationException(HttpServletRequest req,Exception e){
//
//
//        return "对不起，你没有访问权限！";
//    }
//
//
//}
