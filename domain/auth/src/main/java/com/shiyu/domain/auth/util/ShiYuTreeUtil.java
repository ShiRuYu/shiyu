package com.shiyu.domain.auth.util;


import com.shiyu.domain.auth.model.Menu;
import org.apache.commons.collections4.CollectionUtils;
import org.dromara.hutool.core.bean.BeanUtil;
import org.dromara.hutool.core.tree.MapTree;
import org.dromara.hutool.core.tree.TreeNode;
import org.dromara.hutool.core.tree.TreeUtil;

import java.util.List;

public class ShiYuTreeUtil {

    public static List<MapTree<Long>> buildMenuTree(List<Menu> menuList){
        if (CollectionUtils.isEmpty(menuList)){
            return null;
        }
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
}
