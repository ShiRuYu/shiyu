package com.shiyu.web;

import com.shiyu.infrastructure.datasource.model.UserPO;
import com.shiyu.service.biz.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("save")
    public UserPO save(UserPO userPO){
        return userService.save(userPO);
    }
    @GetMapping("findById")
    public UserPO findById(Long id){
        return userService.findById(id);
    }
}
