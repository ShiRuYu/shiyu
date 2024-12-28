package com.shiyu.domain.auth.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.shiyu.domain.auth.model.Menu;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class ShiYuTreeUtil {

    public static List<Tree<Long>> buildMenuTree(List<Menu> menuList){
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
