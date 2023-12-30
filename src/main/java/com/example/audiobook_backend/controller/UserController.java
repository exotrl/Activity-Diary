package com.example.audiobook_backend.controller;

import com.example.audiobook_backend.pojo.StatusCode;
import com.example.audiobook_backend.pojo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author molimark<br />
 * @date: 2022/12/3 11:11<br/>
 * @description: <br/>
 */

@Api(tags = "用户类")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @ApiOperation(value = "用户logout",notes = "从session中移除user字段，成功登出返回200状态码")
    @GetMapping("/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("user");
        return Result.create(StatusCode.OK,"登出成功");
    }

    @ApiOperation(value = "用户login",notes = "账号username密码password登录")
    @PostMapping("/login")
    public Result login(@ApiParam(name = "username",value = "账号",required = true) @RequestParam String username,
                        @ApiParam(name = "password",value = "密码",required = true) @RequestParam String password,
                        HttpSession session){
//        User user = userService.checkUser(username, MD5Utils.code(password));
//        if (user != null) {
//            user.setPassword(null);
//            session.setAttribute("user",user);
//            return "admin/index";
//        } else {
//            attributes.addFlashAttribute("message", "用户名和密码错误");
//            return "redirect:/admin";
//        }
        System.out.println("username"+username);
        return Result.create(StatusCode.OK,"登录成功");
    }
    @GetMapping("/test")
    public boolean test(){
        System.out.println("测试成功");
        return true;
    }

}
