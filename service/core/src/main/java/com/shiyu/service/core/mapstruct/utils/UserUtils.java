package com.shiyu.service.core.mapstruct.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.util.StringUtils;

public class UserUtils {
    public static Long getRoleIdByExtInfo(String extInfo){
        if (StringUtils.hasText(extInfo)){
            JSONObject jsonObject = JSON.parseObject(extInfo);
            return jsonObject.getLong("roleId");
        }
        return null;
    }
}
