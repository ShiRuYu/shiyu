package com.shiyu.infrastructure.datasource.mapstruct;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.domain.auth.model.Menu;
import com.shiyu.infrastructure.datasource.model.MenuPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MenuDBConvertMapper {
    MenuDBConvertMapper INSTANCE = Mappers.getMapper(MenuDBConvertMapper.class);

    MenuPO menuToPO(Menu menu);

    List<MenuPO> menuListToPoList(List<Menu> menu);

    Menu poToMenu(MenuPO menuPO);

    List<Menu> listPoToMenu(List<MenuPO> menuPOList);

    @Mapping(source = "records",target = "data")
    ResultPage<Menu> poPageToMenuPage(PageDTO<MenuPO> menuPOPageDTO);
}
