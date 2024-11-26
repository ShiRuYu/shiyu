package com.shiyu.domain.auth.repository;


import com.shiyu.domain.auth.model.Menu;

import java.util.List;

public interface MenuRepository {
    Menu save(Menu menu);

    Menu update(Menu menu);

    void delete(Long id);

    List<Menu> selectPage(Integer pageNo, Integer pageSize);

    Menu selectById(Long id);

    List<Menu> selectBatchIds(List<Long> menuIdList);
}
