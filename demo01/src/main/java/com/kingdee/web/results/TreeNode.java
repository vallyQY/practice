package com.kingdee.web.results;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 封装树结构
 *
 * @author libin Created on 2019/7/18
 **/
@Data
public class TreeNode implements Serializable {

    private static final long serialVersionUID = 6316057660830935588L;

    private String id;

    private String code;

    private String text;

    public TreeNode() {
    }

    private List<? extends TreeNode> children = new ArrayList<>();

    public static TreeNode of() {
       return new TreeNode();
    }

    public static TreeNode of(String id, String text) {
        return of(id, null, text, null);
    }

    public static TreeNode of(String id, String code, String text) {
        return of(id, code, text, null);
    }

    public static TreeNode of(String id, String text, List<? extends TreeNode> children) {
        return of(id, null, text, children);
    }

    public static TreeNode of(String id, String code, String text, List<? extends TreeNode> children) {
        TreeNode node = new TreeNode();
        node.setId(id);
        node.setCode(code);
        node.setText(text);
        node.setChildren(children);
        return node;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
