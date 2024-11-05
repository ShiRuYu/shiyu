package com.shiyu.infrastructure.datasource.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "sy_user")
public class UserPO implements Serializable {
    @Serial
    private static final long serialVersionUID = -6268366220280617872L;
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
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
    private Integer status;
    /**
     * 0：删除 1：正常
     */
    private Integer delStatus;
}
