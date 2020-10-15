package com.kingdee.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: Regex Util
 */
public final class RegexUtil {

    private RegexUtil() {

    }

    /**
     * 正则中需要被转义的关键字
     */
    private final static Set<Character> RE_KEYS = Sets.newHashSet('$', '(', ')', '*', '+', '.', '[', ']', '?', '\\', '^', '{', '}', '|');
    /**
     * 分组
     */
    private final static Pattern GROUP_VAR = Pattern.compile("\\$(\\d+)");

    private final static Pattern ID_18_CODE_MASK = Pattern.compile("(\\d{6})(\\d{8})(\\d{4}|\\d{3}[xX])");
    private final static Pattern MOBILE_MASK = Pattern.compile("(\\d{3})(\\d{4})(\\d{4})");
    private final static Pattern DATE_MASK = Pattern.compile("(\\d{4})([-/])(\\d{1,2})([-/])(\\d{1,2})");

    private final static Pattern DATE_PATTERN = Pattern.compile("((19|2[0-2])\\d{2})-(0[1-9]|1[0-2])-([0-2][0-9]|3[01])");
    private final static Pattern DATE_TIME_PATTERN = Pattern.compile("((19|2[0-2])\\d{2})-(0[1-9]|1[0-2])-([0-2][0-9]|3[01])\\s([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])");


    /**
     * 验证汉字
     *
     * @param text INPUT
     * @return Boolean
     */
    public static boolean isChinese(String text) {
        if (Strings.isNullOrEmpty(text)) {
            return false;
        }
        return Pattern.matches("^[\u4e00-\u9fa5]+?$", text);
    }

    /**
     * 验证字母开头，字母数字下划线
     *
     * @param text INPUT
     * @return Boolean
     */
    public static boolean isLetterBegin(String text) {
        if (Strings.isNullOrEmpty(text)) {
            return false;
        }
        return Pattern.matches("(^[a-zA-Z])(\\w)*", text);
    }

    /**
     * 验证是否是统一社会信用码
     *
     * @param text INPUT
     * @return Boolean
     */
    public static boolean isShxym(String text) {
        if (Strings.isNullOrEmpty(text)) {
            return false;
        }
        return Pattern.matches("^[0-9ABCDEFGHJKLMNPQRTUWXY]{18}$", text.toUpperCase()) && checkShxym(text.toUpperCase()) || "00000000000000000X".equals(text.toUpperCase());
    }

    private static boolean checkShxym(String text) {
        String checkCode = "0123456789ABCDEFGHJKLMNPQRTUWXY";
        int[] md = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            int idx = checkCode.indexOf(text.charAt(i));
            if (idx < 0) {
                return false;
            }
            sum += idx * md[i];
        }
        sum %= 31;
        return checkCode.charAt((31 - sum) % 31) == text.charAt(17);
    }

    /**
     * 验证工商注册号
     *
     * @param text INPUT
     * @return Boolean
     */
    public static boolean isGszz(String text) {
        if (Strings.isNullOrEmpty(text)) {
            return false;
        }
        return Pattern.matches("^[0-9]{15}$", text) && checkGszz(text);
    }

    private static boolean checkGszz(String text) {
        int p = 10, s = 0;
        for (int i = 0; i < 15; i++) {
            s = (p % 11) + Integer.parseInt(text.substring(i, i + 1));
            p = (s % 10) * 2;
            if (p == 0) {
                p = 20;
            }
        }
        return 1 == s % 10;
    }

    /**
     * 验证组织机构代码
     *
     * @param text INPUT
     * @return Boolean
     */
    public static boolean isZzjg(String text) {
        if (Strings.isNullOrEmpty(text)) {
            return false;
        }
        return Pattern.matches("^[0-9A-Z]{8}(-)?[0-9X]$", text.toUpperCase()) && checkZzjg(text.toUpperCase());
    }

    private static boolean checkZzjg(String text) {
        String checkCode = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] md = {3, 7, 9, 10, 5, 8, 4, 2};
        String cc = "0123456789X0";
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            int a = checkCode.indexOf(text.charAt(i));
            if (a < 0) {
                return false;
            }
            sum += a * md[i];
        }
        sum %= 11;
        return cc.charAt(11 - sum) == text.charAt(8);
    }

    public static boolean isDate(String text) {
        if (Strings.isNullOrEmpty(text)) {
            return false;
        }
        return DATE_PATTERN.matcher(text).matches();
    }

    public static boolean isDateTime(String text) {
        if (Strings.isNullOrEmpty(text)) {
            return false;
        }
        return DATE_TIME_PATTERN.matcher(text).matches();
    }


    /**
     * 18位身份证掩码
     *
     * @param idCode 身份证
     * @return Mask
     */
    public static String mask18IdCode(String idCode) {
        if (StringUtils.isEmpty(idCode)) {
            return idCode;
        }
        if (idCode.trim().length() != 18) {
            return idCode;
        }
        return replaceAll(idCode, ID_18_CODE_MASK, "$1********$3");
    }

    public static String maskMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return mobile;
        }
        if (mobile.trim().length() != 11) {
            return mobile;
        }
        return replaceAll(mobile, MOBILE_MASK, "$1****$3");
    }

    public static String maskDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return date;
        }
        Matcher matcher = DATE_MASK.matcher(date);
        if (!matcher.matches()) {
            return date;
        }
        return replaceAll(date, DATE_MASK, "$1$2**$4**");
    }


    public static String replaceAll(CharSequence content, Pattern pattern, String replacementTemplate) {
        if (StringUtils.isEmpty(content)) {
            return null == content ? null : content.toString();
        }
        final Matcher matcher = pattern.matcher(content);
        boolean result = matcher.find();
        if (result) {
            final Set<String> varNums = findAll(GROUP_VAR, replacementTemplate, 1, new HashSet<String>());
            final StringBuffer sb = new StringBuffer();
            do {
                String replacement = replacementTemplate;
                for (String var : varNums) {
                    int group = Integer.parseInt(var);
                    replacement = replacement.replace("$" + var, matcher.group(group));
                }
                matcher.appendReplacement(sb, escape(replacement));
                result = matcher.find();
            } while (result);
            matcher.appendTail(sb);
            return sb.toString();
        }
        return str(content);
    }

    public static <T extends Collection<String>> T findAll(Pattern pattern, CharSequence content, int group, T collection) {
        if (null == pattern || null == content) {
            return null;
        }

        if (null == collection) {
            throw new NullPointerException("Null collection param provided!");
        }

        final Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            collection.add(matcher.group(group));
        }
        return collection;
    }

    private static String escape(CharSequence content) {
        if (StringUtils.isBlank(content)) {
            return str(content);
        }

        final StringBuilder builder = new StringBuilder();
        int len = content.length();
        char current;
        for (int i = 0; i < len; i++) {
            current = content.charAt(i);
            if (RE_KEYS.contains(current)) {
                builder.append('\\');
            }
            builder.append(current);
        }
        return builder.toString();
    }

    public static String str(CharSequence cs) {
        return null == cs ? null : cs.toString();
    }
}
