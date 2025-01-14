package com.shiyu.bootstrap.isme.result;

import com.shiyu.bootstrap.isme.util.IsmeUtil;
import com.shiyu.bootstrap.isme.mapstract.IsmeMenuConvertMapper;
import com.shiyu.bootstrap.isme.mapstract.IsmeRoleConvertMapper;
import com.shiyu.bootstrap.isme.request.*;
import com.shiyu.commons.utils.AssertUtils;
import com.shiyu.commons.utils.ConvertUtil;
import com.shiyu.commons.utils.ResultPage;
import com.shiyu.commons.utils.enums.RoleEnum;
import com.shiyu.commons.utils.exception.BadRequestException;
import com.shiyu.domain.auth.model.Menu;
import com.shiyu.domain.auth.model.Role;
import com.shiyu.domain.auth.service.AuthService;
import com.shiyu.domain.auth.service.MenuService;
import com.shiyu.domain.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.tree.MapTree;
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
        ResultPage<Role> roleResultPage = roleService.selectPage(request.getName(), ConvertUtil.booleanToInt(request.getEnable()),
                request.getPageNo(), request.getPageSize());
        if (CollectionUtils.isEmpty(roleResultPage.getData())) {
            return ResultPage.success();
        }
        ResultPage<RolePageResult> rolePageResultResultPage = IsmeRoleConvertMapper.INSTANCE.rolePageToPageResult(roleResultPage);
        rolePageResultResultPage.getData().forEach(rolePageResult -> {
            List<Long> permissionIds = authService.selectMenuIdByRoleId(rolePageResult.getId());
            rolePageResult.setPermissionIds(permissionIds);
        });
        return rolePageResultResultPage;
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
        if (StringUtils.equals(RoleEnum.SUPER_ADMIN.getCode(), role.getCode())) {
            throw new BadRequestException("不允许修改超级管理员");
        }
        if (StringUtils.isNotBlank(request.getName())) {
            role.setName(request.getName());
        }
        if (Objects.nonNull(request.getEnable())) {
            role.setStatus(ConvertUtil.booleanToInt(request.getEnable()));
        }
        List<Long> permissionIds = request.getPermissionIds();
        AssertUtils.isNotEmpty(permissionIds,"菜单至少分配一个");

        List<Long> roleIds = authService.selectMenuIdByRoleId(id);
        List<Long> collect = permissionIds.stream()
                .filter(Objects::nonNull)
                .filter(permissionId -> !roleIds.contains(permissionId))
                .toList();

        //todo 优化
        authService.removeMenuByRole(id);
        authService.saveBatchRoleMenu(id, collect);
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
        Role savedRole = roleService.save(role);
        if (CollectionUtils.isNotEmpty(request.getPermissionIds())) {
            authService.saveBatchRoleMenu(savedRole.getId(), request.getPermissionIds());
        }

    }

    public List<MapTree<Long>> findRoleMenuTree(String roleCode) {
        Role role = roleService.selectByCode(roleCode);
        if (Objects.isNull(role)) {
            throw new BadRequestException("当前角色不存在或者已删除");
        }

        List<Menu> menuList =
                RoleEnum.SUPER_ADMIN.getCode().equals(roleCode) ? menuService.selectAll()
                        : authService.selectMenuByRoleId(role.getId());

        List<PermissionResult> permissionResults = IsmeMenuConvertMapper.INSTANCE.menuListToPermissionResultList(menuList);

        return IsmeUtil.buildPermissionTree(permissionResults);
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
