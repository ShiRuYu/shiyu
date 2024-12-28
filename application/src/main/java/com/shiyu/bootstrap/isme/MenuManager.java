package com.shiyu.bootstrap.isme;

import cn.hutool.core.lang.tree.Tree;
import com.shiyu.bootstrap.isme.mapstract.IsmeMenuConvertMapper;
import com.shiyu.bootstrap.isme.request.CreatePermissionRequest;
import com.shiyu.bootstrap.isme.request.UpdatePermissionRequest;
import com.shiyu.bootstrap.isme.result.PermissionResult;
import com.shiyu.bootstrap.isme.util.IsmeUtil;
import com.shiyu.commons.utils.enums.MenuTypeEnum;
import com.shiyu.domain.auth.model.Menu;
import com.shiyu.domain.auth.service.AuthService;
import com.shiyu.domain.auth.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuManager {
    private final AuthService authService;
    private final MenuService menuService;

    public List<Tree<Long>> findAllMenuTree() {
        List<Menu> menuList = menuService.selectByType(MenuTypeEnum.MENU.getCode());

        List<PermissionResult> permissionResults = IsmeMenuConvertMapper.INSTANCE.menuListToPermissionResultList(menuList);

        return IsmeUtil.buildPermissionTree(permissionResults);
    }

    public List<Tree<Long>> findAllTree() {
        List<Menu> menuList = menuService.selectAll();

        List<PermissionResult> permissionResults = IsmeMenuConvertMapper.INSTANCE.menuListToPermissionResultList(menuList);

        return IsmeUtil.buildPermissionTree(permissionResults);
    }

    public void remove(Long id) {
        menuService.delete(id);
    }

    public void create(CreatePermissionRequest request) {
        Menu menu = IsmeMenuConvertMapper.INSTANCE.createPermissionRequestToMenu(request);
        menuService.save(menu);
    }

    public void update(Long id, UpdatePermissionRequest request) {
        Menu menu = IsmeMenuConvertMapper.INSTANCE.updatePermissionRequestToMenu(request);
        menu.setId(id);
        menuService.update(menu);
    }

    public List<PermissionResult> findButtonByPid(Long parentId) {
        List<Menu> menus = menuService.selectByTypeAndPid(parentId, MenuTypeEnum.BUTTON.getCode());
        return IsmeMenuConvertMapper.INSTANCE.menuListToPermissionResultList(menus);
    }

    public void batchCreate(List<CreatePermissionRequest> request) {
        List<Menu> menuList = IsmeMenuConvertMapper.INSTANCE.createPermissionListRequestToMenuList(request);
        menuService.insertBatchSomeColumn(menuList);
    }

    public List<PermissionResult> findAllMenu() {
        List<Menu> menus = menuService.selectByType(MenuTypeEnum.MENU.getCode());
        return IsmeMenuConvertMapper.INSTANCE.menuListToPermissionResultList(menus);
    }

    public PermissionResult findOne(Long id) {
        return IsmeMenuConvertMapper.INSTANCE.menuToPermissionResult(menuService.selectById(id));
    }

    public Boolean validateMenuPath(String path) {
        return menuService.checkPath(path);
    }
}
