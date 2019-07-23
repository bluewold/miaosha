package com.miaosha.code.hello;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@Api(value = "/",description="这是初始测试")
public class helloController {
    @RequestMapping("/")
    @ResponseBody
    @ApiOperation(value="通过这个方法可以获取cookies",httpMethod = "GET")
    String home(){
        return "hello world";
    }

    public static void main(String[] args) throws Exception{
        SpringApplication.run(helloController.class,args);
    }
}
