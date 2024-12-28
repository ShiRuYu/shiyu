package com.shiyu.domain.auth.service;

import com.shiyu.commons.utils.ResultPage;
import com.shiyu.commons.utils.enums.MenuTypeEnum;
import com.shiyu.domain.auth.model.Menu;

import java.util.List;

public interface MenuService {
    Menu save(Menu menu);

    Menu update(Menu menu);

    void delete(Long id);

    ResultPage<Menu> selectPage(Integer pageNo, Integer pageSize);

    Menu selectById(Long id);

    List<Menu> selectBatchIds(List<Long> menuIdList);

    List<Menu> selectAll();

    List<Menu> selectByType(String type);

    void insertBatchSomeColumn(List<Menu> menuList);

    List<Menu> selectByTypeAndPid(Long parentId, String type);

    Boolean checkPath(String path);
}
