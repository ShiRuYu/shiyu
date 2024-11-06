package com.shiyu.web.controller;

import com.shiyu.service.biz.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("role")
public class RoleController {
    private final RoleService roleService;

}
