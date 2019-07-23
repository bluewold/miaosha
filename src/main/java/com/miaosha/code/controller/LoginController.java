package com.miaosha.code.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.miaosha.code.redis.RedisService;
import com.miaosha.code.result.Result;
import com.miaosha.code.service.MiaoshaUserService;
import com.miaosha.code.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Api(value = "/login",description="这是登陆的接口")
@RequestMapping("/login")
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
    MiaoshaUserService userService;
	
	@Autowired
    RedisService redisService;
	
    @RequestMapping("/to_login")
    @ApiOperation(value="获取登陆界面",httpMethod = "GET")
    public String toLogin() {
        return "login";
    }
    
    @RequestMapping("/do_login")
    @ApiOperation(value="进行登陆",httpMethod = "POST")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
    	log.info(loginVo.toString());
    	//登录
    	String token = userService.login(response, loginVo);
    	return Result.success(token);
    }
}
