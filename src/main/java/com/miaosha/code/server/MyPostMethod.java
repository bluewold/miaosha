package com.miaosha.code.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description="这是我全部post方法")
@RequestMapping("/v1")
public class MyPostMethod {
    private static Cookie cookie;

    //登陆成功获取cookie，然后再访问其他接口
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ApiOperation(value="登陆接口，成功后获取cokkie信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value="userName",required=true) String userName,
                        @RequestParam(value="password",required=true) String password){
        if(userName.equals("zhangsan") && password.equals("123456")){
            Cookie cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登陆成功了";
        }
        return  "用户名或者密码错误";
    }

    /*@RequestMapping(value="/getUserList",method=RequestMethod.POST)
    @ApiOperation(value="获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u){
        Cookie[] cookies = request.getCookies();
        User user ;
        for(Cookie c: cookies){
            if(c.getName().equals("login") && c.getValue().equals("true")
            && u.getUserName().equals("zhangsan")
            && u.getPassword().equals("123456")){
                user = new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSez("man");
                return user.toString();
            }
        }
        return "参数不合法";
    }*/
}
