package com.shiyu.service.biz.model.web;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class UserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -6268366220280617872L;
    /**
     * ID
     */
    private Long id;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
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
    private String birthday;
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
     * 拥有的角色
     */
    private List<RoleVO> roles;

    /**
     * 当前角色
     */
    private RoleVO currentRole;
}
