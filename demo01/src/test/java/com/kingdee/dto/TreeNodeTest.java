package com.kingdee.dto;

import org.junit.Test;

/**
 * description:
 *
 * @author qy
 * @version v1.0
 * @date Created in 2020/10/14
 */
public class TreeNodeTest {

    @Test
    public void testNew(){
        TreeNode treeNode = new TreeNode();
        treeNode.setId("1");
        System.out.println(treeNode.getId());
    }
}
