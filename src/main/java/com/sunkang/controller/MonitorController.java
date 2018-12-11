package com.sunkang.controller;

import com.sunkang.IDao.MonitorMapper;
import com.sunkang.entity.Monitor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MonitorController {

    @Autowired
    private MonitorMapper monitorMapper;

    /**
     * 自己的监控
     */
    @ResponseBody
    @RequestMapping(value = "monitor")
    public List<Monitor> monitor(){
        List<Monitor> list= monitorMapper.selectMonitor();
        return list;
    }

    /**
     * 返回cms的监控
     *
     * **/
    @ResponseBody
    @RequestMapping(value = "monitorHtml")
    public String monitorHtml() throws Exception {
        //登录
        Map<String, String> datas = new HashMap<>();
        datas.put("username", "admin");
        datas.put("password", "kang");
        Connection con2 = Jsoup
                .connect("http://www.sunkang.xyz:8080/publiccms/admin/login.do");
        con2.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        // 设置cookie和post上面的map数据
        Connection.Response login = con2.ignoreContentType(true).method(Connection.Method.POST)
                .data(datas).execute();
        Map<String, String> map = login.cookies();//cookie信息

        //直接用登陆后拿到的cookies请求数据
        Connection con3 = Jsoup.connect("http://www.sunkang.xyz:8080/publiccms/admin/sysSite/monitor.html");// 获取连接
        con3.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");// 配置模拟浏览器
        Connection.Response login3 = con3.ignoreContentType(true).method(Connection.Method.GET).cookies(map).execute();
        String monitorHtml=login3.body().replaceAll("CMS系统监控","系统监控").replaceAll("../sysSite/memory.html","memoryHtml");
        return monitorHtml;
    }

    /**
     * 返回cms的监控
     *
     * **/
    @ResponseBody
    @RequestMapping(value = "memoryHtml")
    public String memoryHtml() throws Exception {
        //登录
        Map<String, String> datas = new HashMap<>();
        datas.put("username", "admin");
        datas.put("password", "kang");
        Connection con2 = Jsoup
                .connect("http://www.sunkang.xyz:8080/publiccms/admin/login.do");
        con2.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        // 设置cookie和post上面的map数据
        Connection.Response login = con2.ignoreContentType(true).method(Connection.Method.POST)
                .data(datas).execute();
        Map<String, String> map = login.cookies();//cookie信息

        //直接用登陆后拿到的cookies请求数据
        Connection con3 = Jsoup.connect("http://www.sunkang.xyz:8080/publiccms/admin/sysSite/memory.html");// 获取连接
        con3.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");// 配置模拟浏览器
        Connection.Response login3 = con3.ignoreContentType(true).method(Connection.Method.GET).cookies(map).execute();
        String monitorHtml=login3.body();
        return monitorHtml;
    }

}
