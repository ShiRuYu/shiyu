package com.shiyu.service.core.manager.Impl;

import com.shiyu.infrastructure.datasource.repository.MenuRepository;
import com.shiyu.service.core.manager.MenuManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuManagerImpl implements MenuManager {
    private final MenuRepository menuRepository;
}
