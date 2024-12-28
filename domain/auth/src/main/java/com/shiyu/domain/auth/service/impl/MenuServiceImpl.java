package com.shiyu.domain.auth.service.impl;

import com.shiyu.commons.utils.ResultPage;
import com.shiyu.commons.utils.enums.MenuTypeEnum;
import com.shiyu.domain.auth.model.Menu;
import com.shiyu.domain.auth.repository.MenuRepository;
import com.shiyu.domain.auth.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu update(Menu menu) {
        return menuRepository.update(menu);
    }

    @Override
    public void delete(Long id) {
        menuRepository.delete(id);
    }

    @Override
    public ResultPage<Menu> selectPage(Integer pageNo, Integer pageSize) {
        return menuRepository.selectPage(pageNo, pageSize);
    }

    @Override
    public Menu selectById(Long id) {
        return menuRepository.selectById(id);
    }

    @Override
    public List<Menu> selectBatchIds(List<Long> menuIdList) {
        return menuRepository.selectBatchIds(menuIdList);
    }

    @Override
    public List<Menu> selectAll() {
        return menuRepository.selectAll();
    }

    @Override
    public List<Menu> selectByType(String type) {
        return menuRepository.selectByType(type);
    }

    @Override
    public void insertBatchSomeColumn(List<Menu> menuList) {
        menuRepository.insertBatchSomeColumn(menuList);
    }

    @Override
    public List<Menu> selectByTypeAndPid(Long parentId, String type) {
        return menuRepository.selectByTypeAndPid(parentId,type);
    }

    @Override
    public Boolean checkPath(String path) {
        return menuRepository.checkPath(path);
    }
}
