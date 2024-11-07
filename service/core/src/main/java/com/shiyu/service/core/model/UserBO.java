package com.shiyu.service.core.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserBO {
    /**
     * ID
     */
    private Long id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 手机
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 地址
     */
    private String addr;
    /**
     * int	性别 0：保密 1：男 2：女
     */
    private Integer gender;
    /**
     * 个性签名
     */
    private String signature;
    /**
     * 扩展信息
     */
    private String extInfo;

    /**
     * 拥有角色
     */
    private List<Long> roleIds;
    /**
     * 当前角色
     */
    private Long currentRoleId;

}
