package com.shiyu.core.infrastructure.datasource.mapstruct;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.core.domain.auth.model.Menu;
import com.shiyu.core.infrastructure.datasource.model.MenuPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MenuConvertMapper {
    MenuConvertMapper INSTANCE = Mappers.getMapper(MenuConvertMapper.class);

    MenuPO detailToPo(Menu menu);

    Menu poToDetail(MenuPO menuPO);

    List<Menu> listPoToDetail(List<MenuPO> menuPOList);
    @Mappings(
            @Mapping(source = "records",target = "data")
    )
    ResultPage<Menu> poPageToDetailPage(PageDTO<MenuPO> menuPOPageDTO);
}
