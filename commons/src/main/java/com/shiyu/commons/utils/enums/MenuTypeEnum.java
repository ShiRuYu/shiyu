package com.shiyu.commons.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    MENU("MENU", "菜单"),
    BUTTON("BUTTON", "按钮"),
            ;
    private final String code;
    private final String desc;

}
