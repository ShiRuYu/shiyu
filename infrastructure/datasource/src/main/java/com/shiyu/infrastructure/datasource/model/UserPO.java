package com.shiyu.infrastructure.datasource.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shiyu.framework.mybatisplus.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sy_user")
public class UserPO extends BaseDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -6268366220280617872L;
    /**
     * ID
     */
    private Long id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户名
     */
    private String username;
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
    private LocalDateTime birthday;
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
     * 密码
     */
    private String password;
    /**
     * 扩展信息
     */
    private String extInfo;
    /**
     * 0:活跃
     */
    private Integer status = 0;
}
