package com.kingdee.dto;

import lombok.Data;

import java.util.List;

/**
 * description:
 *
 * @author qy
 * @version v1.0
 * @date Created in 2020/10/14
 */
@Data
public class TreeNode{
    private String id;
    private String code;
    private String text;
    private List<? extends TreeNode> children;


}
