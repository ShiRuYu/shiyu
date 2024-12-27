package com.shiyu.bootstrap.isme.result;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.shiyu.bootstrap.isme.mapstract.IsmeMenuConvertMapper;
import com.shiyu.bootstrap.isme.mapstract.IsmeRoleConvertMapper;
import com.shiyu.bootstrap.isme.request.*;
import com.shiyu.commons.utils.ConvertUtil;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.commons.utils.RoleEnum;
import com.shiyu.commons.utils.exception.BadRequestException;
import com.shiyu.domain.auth.model.Menu;
import com.shiyu.domain.auth.model.Role;
import com.shiyu.domain.auth.service.AuthService;
import com.shiyu.domain.auth.service.MenuService;
import com.shiyu.domain.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RoleManager {
    private final AuthService authService;
    private final RoleService roleService;
    private final MenuService menuService;


    public ResultPage<RolePageResult> findPage(RolePageRequest request) {
        ResultPage<Role> roleResultPage = roleService.selectPage(request.getName(), ConvertUtil.booleanToInt(request.getEnable()), request.getPageNo(), request.getPageSize());
        return IsmeRoleConvertMapper.INSTANCE.rolePageToPageResult(roleResultPage);
    }

    public List<RoleResult> findAll(Boolean enable) {
        List<Role> roles = roleService.selectAll(ConvertUtil.booleanToInt(enable));
        return IsmeRoleConvertMapper.INSTANCE.roleListToRoleResultList(roles);
    }

    public void updateRole(Long id, UpdateRoleRequest request) {
        Role role = roleService.selectById(id);
        if (role == null) {
            throw new BadRequestException("角色不存在或者已删除");
        }
        if (RoleEnum.getRoleCodes().contains(role.getCode())) {
            throw new BadRequestException("不允许修改超级管理员");
        }
        if (StringUtils.isNotBlank(request.getName())) {
            role.setName(request.getName());
        }
        if (ObjectUtil.isNotNull(request.getEnable())) {
            role.setStatus(ConvertUtil.booleanToInt(request.getEnable()));
        }
        roleService.update(role);
    }

    public void remove(Long id) {
        roleService.delete(id);
    }

    public void removeRoleUsers(Long roleId, RemoveRoleUsersRequest request) {
        authService.removeBatchUserRole(roleId, request.getUserIds());
    }

    public void addRoleUsers(Long roleId, AddRoleUsersRequest request) {
        authService.saveBatchRoleUser(roleId, request.getUserIds());
    }

    public void createRole(CreateRoleRequest request) {
        Boolean exists = roleService.checkCode(request.getCode());
        if (exists) {
            throw new BadRequestException("角色已存在（角色名和角色编码不能重复）");
        }
        Role role = IsmeRoleConvertMapper.INSTANCE.createRoleRequestToRole(request);
        roleService.save(role);
    }

    public List<Tree<Long>> findRoleMenuTree(String roleCode) {
        Role role = roleService.selectByCode(roleCode);
        if (Objects.isNull(role)) {
            throw new BadRequestException("当前角色不存在或者已删除");
        }

        List<Menu> menuList =
                RoleEnum.SUPER_ADMIN.getCode().equals(roleCode) ? menuService.selectAll()
                        : authService.selectMenuByRoleId(role.getId());
        if (CollectionUtils.isNotEmpty(menuList)) {
            List<TreeNode<Long>> nodes = menuList.stream().map(menu -> {
                TreeNode<Long> treeNode = new TreeNode<>();
                treeNode.setId(menu.getId());
                treeNode.setParentId(menu.getParentId());
                treeNode.setWeight(menu.getSort());
                treeNode.setName(menu.getName());
                treeNode.setExtra(BeanUtil.beanToMap(menu));
                return treeNode;
            }).toList();
            return TreeUtil.build(nodes, 0L);
        }
        return null;
    }

    public List<PermissionResult> findRoleMenu(Long id) {
        List<Menu> menus = authService.selectMenuByRoleId(id);
        return IsmeMenuConvertMapper.INSTANCE.menuListToPermissionResultList(menus);
    }

    public RoleResult findOne(Long id) {
        return IsmeRoleConvertMapper.INSTANCE.roleToRoleResult(roleService.selectById(id));
    }

    public void addRolePermissions(Long id, List<Long> permissionIds) {
        authService.saveBatchRoleMenu(id, permissionIds);
    }
}
