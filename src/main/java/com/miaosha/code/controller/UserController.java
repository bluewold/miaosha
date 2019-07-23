package com.miaosha.code.controller;

import com.miaosha.code.domain.MiaoshaUser;
import com.miaosha.code.redis.RedisService;
import com.miaosha.code.result.Result;
import com.miaosha.code.service.MiaoshaUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@Api(value = "/user",description="获取用户信息接口")
@RequestMapping("/user")
public class UserController {

	@Autowired
    MiaoshaUserService userService;
	
	@Autowired
    RedisService redisService;
	
    @RequestMapping("/info")
    @ApiOperation(value="获取用户信息",httpMethod = "GET")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model, MiaoshaUser user) {
        return Result.success(user);
    }
    
}
