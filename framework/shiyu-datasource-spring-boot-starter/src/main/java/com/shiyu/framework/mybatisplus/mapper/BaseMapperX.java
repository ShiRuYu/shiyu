package com.shiyu.framework.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

public interface BaseMapperX<T> extends BaseMapper<T> {
    int insertBatchSomeColumn(Collection<T> entityList);
}

