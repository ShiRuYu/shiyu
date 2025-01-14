package com.shiyu.bootstrap.isme.util;


import com.shiyu.bootstrap.isme.result.PermissionResult;
import org.apache.commons.collections4.CollectionUtils;
import org.dromara.hutool.core.bean.BeanUtil;
import org.dromara.hutool.core.tree.MapTree;
import org.dromara.hutool.core.tree.TreeNode;
import org.dromara.hutool.core.tree.TreeUtil;

import java.util.List;

public class IsmeUtil {
    public static List<MapTree<Long>> buildPermissionTree(List<PermissionResult> permissionResults){
        if (CollectionUtils.isEmpty(permissionResults)){
            return null;
        }
        List<TreeNode<Long>> nodes = permissionResults.stream().map(permission -> {
            TreeNode<Long> treeNode = new TreeNode<>();
            treeNode.setId(permission.getId());
            treeNode.setParentId(permission.getParentId());
            treeNode.setWeight(permission.getOrder());
            treeNode.setName(permission.getName());
            treeNode.setExtra(BeanUtil.beanToMap(permission));
            return treeNode;
        }).toList();
        return TreeUtil.build(nodes, 0L);
    }
}
