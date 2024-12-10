package com.shiyu.core.domain.auth.service;

import com.shiyu.commons.utils.ResultPage;
import com.shiyu.core.domain.auth.model.Menu;

import java.util.List;

public interface MenuService {
    Menu save(Menu menu);

    Menu update(Menu menu);

    void delete(Long id);

    ResultPage<Menu> selectPage(Integer pageNo, Integer pageSize);

    Menu selectById(Long id);

    List<Menu> selectBatchIds(List<Long> menuIdList);
}
