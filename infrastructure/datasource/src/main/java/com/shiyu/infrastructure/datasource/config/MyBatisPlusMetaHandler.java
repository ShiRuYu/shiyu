package com.shiyu.infrastructure.datasource.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * <p>
 * 新增，更新时，自动更新字段
 * </p>
 strictInsertFill 和 strictUpdateFill：分别用于插入和更新操作时自动填充字段。这两个方法要求指定字段名、字段类型和填充值。
 fillStrategy：定义了在插入或更新时如何填充字段的策略（如仅插入时填充、仅更新时填充或插入和更新时都填充）。
 strictFillStrategy：是一种更严格的填充策略，只有当字段的值不为 null 时才会填充，否则不会填充。
 */
@Component
public class MyBatisPlusMetaHandler implements MetaObjectHandler {

    //新增操作
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, now);
    }
}