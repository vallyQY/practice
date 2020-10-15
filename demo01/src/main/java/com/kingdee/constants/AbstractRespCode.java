package com.kingdee.constants;

/**
 * Response code for Json result
 *
 * @author code
 */
public abstract class AbstractRespCode {
    public static final String OK = "0";
    public static final String FAILED = "-1";
    /**
     * 图形验证码不匹配
     */
    public static final String CAPTCHA_MISMATCH = "-2";
    /**
     * 手机验证码错误
     */
    public static final String PHONE_VC_WRONG = "-3";
    /**
     * 身份证与名称不匹配
     */
    public static final String IDCODE_MISMATCH = "-4";
    /**
     * Token错误
     */
    public static final String TOKEN_MISMATCH = "403";
}
