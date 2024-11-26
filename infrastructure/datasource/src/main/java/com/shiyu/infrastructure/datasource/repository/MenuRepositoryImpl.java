package com.shiyu.infrastructure.datasource.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.domain.auth.model.Menu;
import com.shiyu.domain.auth.repository.MenuRepository;
import com.shiyu.infrastructure.datasource.mapper.MenuMapper;
import com.shiyu.infrastructure.datasource.mapstruct.MenuConvertMapper;
import com.shiyu.infrastructure.datasource.model.MenuPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepository {
    private final MenuMapper mapper;

    @Override
    public Menu save(Menu menu) {
        mapper.insert(MenuConvertMapper.INSTANCE.detailToPo(menu));
        MenuPO menuPO = mapper.selectById(menu.getId());
        return MenuConvertMapper.INSTANCE.poToDetail(menuPO);
    }

    @Override
    public Menu update(Menu menu) {
        mapper.updateById(MenuConvertMapper.INSTANCE.detailToPo(menu));
        MenuPO menuPO = mapper.selectById(menu.getId());
        return MenuConvertMapper.INSTANCE.poToDetail(menuPO);
    }

    @Override
    public void delete(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public List<Menu> selectPage(Integer pageNo, Integer pageSize) {
        // queryWrapper组装查询where条件
        LambdaQueryWrapper<MenuPO> queryWrapper = new LambdaQueryWrapper<>();
        // 分页参数
        PageDTO<MenuPO> menuPOPageDTO = mapper.selectPage(new PageDTO<>(pageNo, pageSize), queryWrapper);

        return MenuConvertMapper.INSTANCE.listPoToDetail(menuPOPageDTO.getRecords());
    }

    @Override
    public Menu selectById(Long id){
        MenuPO menuPO = mapper.selectById(id);
        return MenuConvertMapper.INSTANCE.poToDetail(menuPO);
    }

    @Override
    public List<Menu> selectBatchIds(List<Long> menuIdList) {
        List<MenuPO> menuPOList = mapper.selectBatchIds(menuIdList);
        return MenuConvertMapper.INSTANCE.listPoToDetail(menuPOList);
    }
}
