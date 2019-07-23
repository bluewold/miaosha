package com.miaosha.code.server;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description="这是我全部get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getCookies",method= RequestMethod.GET)
    @ApiOperation(value="通过这个方法可以获取cookies")
    public String getCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("login","True");
        response.addCookie(cookie);
        return  "恭喜你获取cookie";
    }

    @RequestMapping(value = "/get/with/Cookies",method= RequestMethod.GET)
    @ApiOperation(value="需要携带cookie进行访问")
    public String getwithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "你必须带着cookie来";
        }
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("login") &&
            cookie.getValue().equals("True")){
                return "恭喜你访问成功";
            }
        }
        return "你必须带着cookie来";
    }

    @RequestMapping(value="/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value="分页get请求")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("裙子",4000);
        return myList;
    }
}
