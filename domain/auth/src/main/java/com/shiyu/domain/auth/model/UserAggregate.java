package com.shiyu.domain.auth.model;

import cn.hutool.crypto.digest.BCrypt;
import com.shiyu.commons.utils.AssertUtils;
import com.shiyu.commons.utils.BizResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAggregate {
    /**
     * ID
     */
    private Long id;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
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
    private Map<String,Object> extInfo;
    /**
     * 0:活跃
     */
    private Integer status;
    /**
     * 0：删除 1：正常
     */
    private Integer delStatus;

    /**
     * 拥有角色
     */
    private List<Role> roleList;

    public Role getCurrentRole(String roleCode){
        if (StringUtils.isNotBlank(roleCode)){
            return roleList.stream().filter(role -> role.getCode().equals(roleCode)).findFirst().orElse(null);
        }
        String currentRoleCode = MapUtils.getString(extInfo, "currentRoleCode", null);
        if (StringUtils.isNotBlank(currentRoleCode)){
            return roleList.stream().filter(role -> role.getCode().equals(currentRoleCode)).findFirst().orElse(null);
        }
        return roleList.stream().findFirst().orElse(null);
    }
    public void checkPwd(String newPwd, String oldPwd){
        //登录
        AssertUtils.isTrue(BCrypt.checkpw(newPwd, oldPwd), BizResultCode.ERR_10004);
    }

}
