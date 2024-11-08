package com.shiyu.web.controller;

import com.google.common.collect.Lists;
import com.shiyu.service.biz.model.web.UserDTO;
import com.shiyu.service.biz.model.web.UserVO;
import com.shiyu.service.biz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    /**
     * 用户列表-分页
     */
    @GetMapping("list")
    public List<UserVO> list(Integer pageNo, Integer pageSize){
        return Lists.newArrayList();
    }

    /**
     * 用户详情
     */
    @GetMapping("detail")
    public UserVO detail(Long id){
        return userService.detail(id);
    }
    /**
     * 删除用户
     */
    @PostMapping("delete")
    public UserVO delete(Long id){
        return null;
    }
    /**
     * 修改用户
     */
    @PostMapping("update")
    public UserVO update(UserDTO userDTO){
        return null;
    }
    /**
     * 新增用户
     */
    @PostMapping("add")
    public UserVO save(UserDTO userDTO){
        return userService.save(userDTO);
    }
}
