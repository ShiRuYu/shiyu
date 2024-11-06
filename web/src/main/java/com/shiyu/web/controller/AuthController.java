package com.shiyu.web.controller;

import com.shiyu.web.model.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    /**
     * 登录
     */
    @PostMapping("login")
    public UserVO login(String username, String password, String captcha){
        return null;
    }
    /**
     * 验证码
     */
    @PostMapping("captcha")
    public UserVO captcha(){
        return null;
    }
    /**
     * 切换角色
     */
    @PostMapping("/current-role/switch")
    public UserVO captcha(String roleCode){
        return null;
    }
    /**
     * 退出登录
     */
    @PostMapping("logout")
    public UserVO logout(){
        return null;
    }

}
