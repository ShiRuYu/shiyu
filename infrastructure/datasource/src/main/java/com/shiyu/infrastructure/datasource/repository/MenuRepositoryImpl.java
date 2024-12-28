package com.shiyu.infrastructure.datasource.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.domain.auth.model.Menu;
import com.shiyu.domain.auth.repository.MenuRepository;
import com.shiyu.infrastructure.datasource.mapper.MenuMapper;
import com.shiyu.infrastructure.datasource.mapstruct.MenuDBConvertMapper;
import com.shiyu.infrastructure.datasource.model.MenuPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepository {
    private final MenuMapper menuMapper;

    @Override
    public Menu save(Menu menu) {
        menuMapper.insert(MenuDBConvertMapper.INSTANCE.menuToPO(menu));
        MenuPO menuPO = menuMapper.selectById(menu.getId());
        return MenuDBConvertMapper.INSTANCE.poToMenu(menuPO);
    }

    @Override
    public Menu update(Menu menu) {
        MenuPO updatePO = MenuDBConvertMapper.INSTANCE.menuToPO(menu);

        LambdaUpdateWrapper<MenuPO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(MenuPO::getId, updatePO.getId());
        menuMapper.update(updatePO, updateWrapper);

        MenuPO menuPO = menuMapper.selectById(menu.getId());
        return MenuDBConvertMapper.INSTANCE.poToMenu(menuPO);
    }

    @Override
    public void delete(Long id) {
        menuMapper.deleteById(id);
    }

    @Override
    public ResultPage<Menu> selectPage(Integer pageNo, Integer pageSize) {
        // queryWrapper组装查询where条件
        LambdaQueryWrapper<MenuPO> queryWrapper = new LambdaQueryWrapper<>();
        // 分页参数
        PageDTO<MenuPO> menuPOPageDTO = menuMapper.selectPage(new PageDTO<>(pageNo, pageSize), queryWrapper);

        return MenuDBConvertMapper.INSTANCE.poPageToMenuPage(menuPOPageDTO);
    }

    @Override
    public Menu selectById(Long id){
        MenuPO menuPO = menuMapper.selectById(id);
        return MenuDBConvertMapper.INSTANCE.poToMenu(menuPO);
    }

    @Override
    public List<Menu> selectBatchIds(List<Long> menuIdList) {
        List<MenuPO> menuPOList = menuMapper.selectBatchIds(menuIdList);
        return MenuDBConvertMapper.INSTANCE.listPoToMenu(menuPOList);
    }

    @Override
    public List<Menu> selectAll() {
        List<MenuPO> menuPOList = menuMapper.selectList(new LambdaQueryWrapper<>());
        return MenuDBConvertMapper.INSTANCE.listPoToMenu(menuPOList);
    }

    @Override
    public List<Menu> selectByType(String type) {
        List<MenuPO> menuPOList = menuMapper.selectList(new LambdaQueryWrapper<MenuPO>().eq(MenuPO::getType, type));
        return MenuDBConvertMapper.INSTANCE.listPoToMenu(menuPOList);
    }

    @Override
    public void insertBatchSomeColumn(List<Menu> menuList) {
        List<MenuPO> menuPOList = MenuDBConvertMapper.INSTANCE.menuListToPoList(menuList);
        menuMapper.insertBatchSomeColumn(menuPOList);

    }

    @Override
    public List<Menu> selectByTypeAndPid(Long parentId, String type) {
        LambdaQueryWrapper<MenuPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MenuPO::getType, type);
        wrapper.eq(MenuPO::getParentId, parentId);
        List<MenuPO> menuPOList = menuMapper.selectList(wrapper);
        return MenuDBConvertMapper.INSTANCE.listPoToMenu(menuPOList);
    }

    @Override
    public Boolean checkPath(String path) {
        return menuMapper.exists(new LambdaQueryWrapper<MenuPO>().eq(MenuPO::getRoutePath, path));
    }
}
