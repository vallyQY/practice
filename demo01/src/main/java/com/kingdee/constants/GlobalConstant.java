package com.kingdee.constants;

/**
 *
 * @author coder
 **/
public interface GlobalConstant {

    String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    String DATE_1_PATTERN = "yyyy-MM-dd";
    String DATE_2_PATTERN = "yyyy/MM/dd";
    String DATE_3_PATTERN = "yyyyMMdd";
    String YEAR_MONTH_PATTERN = "yyyy-MM";
    String YEAR_PATTERN = "yyyy";
    String TIME_PATTERN = "HH:mm:ss";


    /**
     * 验证码key
     */
    String VERIFICATION_CODE = "verificationCode";

    Integer DEFAULT_USERNAME_LENGTH = 6;
    Integer DEFAULT_PASSWORD_LENGTH = 6;

    /**
     * 全局缓存名称
     */
    String BASE_REDIS_CACHE_PREFIX = "platform:base:caches:";
    String REDIS_SQL_KEYWORD = BASE_REDIS_CACHE_PREFIX + "sql:keywords";
    String DS_REDIS_CACHE_PREFIX = "platform:dataService:caches:";

    /**
     * 导入缓存名称
     */
    String REDIS_RELATE_BASE_KEY = "import:redis:base:";

    /**
     * 信用报告前缀
     */
    String CREDIT_REPORT_KEY = "eval:redis:code:";

    /**
     * 批量导入增补前缀
     */
    String IMPORT_THREAD_LEG_ADD = "leg_base_add:job";

    /**
     * 批量导入补码前缀
     */
    String IMPORT_THREAD_LEG_INFO = "leg_base_supply:job";

    /**
     * 批量导入增补前缀
     */
    String IMPORT_THREAD_NAT_ADD = "nat_base_add:job";

    String DS_RATE = "rate:";

    /**
     * 评级计算前缀
     */
    String EVAL_CAL_RESP = "eval:redis:cal:resp";
    String EVAL_CAL_TEMPLATE = "eval:redis:cal:template";
    String EVAL_CAL_RESOURCE = "eval:redis:cal:resource";
    String EVAL_CAL_VAR= "eval:redis:cal:var";
    String EVAL_CAL_RULE= "eval:redis:cal:rule";

    /**
     * 单据元数据前缀
     */

    String FORM_META_XML_REDIS_KEY ="form:meta:redis:xml";
}
