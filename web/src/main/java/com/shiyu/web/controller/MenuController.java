package com.shiyu.web.controller;

import com.shiyu.service.biz.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("menu")
public class MenuController {
    private final MenuService menuService;

}
