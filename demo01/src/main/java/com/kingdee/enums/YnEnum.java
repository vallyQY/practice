package com.kingdee.enums;


import lombok.Getter;

/**
 * description:
 *
 * @author qy
 * @version v1.0
 * @date Created in 2020/10/15
 */
public enum YnEnum {
    /**
     * 否
     */
    N("0", "否"),

    /**
     * 是
     */
    Y("1", "是");

    @Getter
    private String code;
    @Getter
    private String text;

    YnEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
