package com.shiyu.framework.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.shiyu.framework.mybatisplus.dataobject.BaseDO;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 新增，更新时，自动更新字段
 * </p>

 strictInsertFill	插入操作时自动填充字段	严格的插入填充，要求字段可插入并填充提供的值
 strictUpdateFill	更新操作时自动填充字段	严格的更新填充，要求字段可更新并填充提供的值
 fillStrategy	动态设置字段的填充策略	控制哪些字段在插入/更新时填充 eg:this.fillStrategy(metaObject, "createTime", FieldFill.INSERT);
 strictFillStrategy	严格控制字段填充策略	更严格的策略，通常用于特殊需求
 setFieldValByName	手动设置字段值	用于手动设置字段值，而不是自动填充 eg:metaObject.setFieldValByName("updateTime", LocalDateTime.now());
 */
public class DefaultFieldMetaHandler implements MetaObjectHandler {

    //新增操作
    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseDO baseDO) {

            LocalDateTime current = LocalDateTime.now();
            // 创建时间为空，则以当前时间为插入时间
            if (Objects.isNull(baseDO.getCreateTime())) {
                baseDO.setCreateTime(current);
            }
            // 更新时间为空，则以当前时间为更新时间
            if (Objects.isNull(baseDO.getUpdateTime())) {
                baseDO.setUpdateTime(current);
            }

        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间为空，则以当前时间为更新时间
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}