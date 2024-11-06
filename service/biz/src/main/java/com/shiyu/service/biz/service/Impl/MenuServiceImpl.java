package com.shiyu.service.biz.service.Impl;

import com.shiyu.service.biz.service.MenuService;
import com.shiyu.service.core.manager.MenuManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuManager menuManager;
}
