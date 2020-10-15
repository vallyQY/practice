package com.kingdee.utils;

import com.google.common.collect.Maps;
import com.kingdee.constants.GlobalConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Description: 通用工具类
 *
 * @author coder
 * Created on 2019/4/28
 **/
public class CommUtils {

    /**
     * 以UTF-8编码进行URL encode，返回编码后的字符串
     */
    public static String encodeURL(String s) {
        try {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("无法进行URL encode，不支持UTF-8编码", e);
        }
    }

    public static String uuid() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", StringUtils.EMPTY);
    }

    public static String randomUUID() {
        SnowflakeIdWorker worker = SnowflakeIdWorker.getInstance(0, 1);
        return "" + worker.nextId();
    }

    public static Map<String, Object> formatDate(Map<String, Object> map) {
        Map<String, Object> data = Maps.newLinkedHashMap();
        map.forEach((key, value) -> {
            if (Objects.nonNull(value)) {
                String pattern = GlobalConstant.DATE_TIME_PATTERN;
                if (value instanceof LocalDateTime) {
                    data.put(key, ((LocalDateTime) value).format(DateTimeFormatter.ofPattern(GlobalConstant.DATE_TIME_PATTERN)));
                } else if (value instanceof LocalDate) {
                    data.put(key, ((LocalDate) value).format(DateTimeFormatter.ofPattern(GlobalConstant.DATE_1_PATTERN)));
                } else if (value instanceof Timestamp) {
                    data.put(key, DateFormatUtils.format((Date) value, pattern));
                } else if (value instanceof Date) {
                    data.put(key, DateFormatUtils.format((Date) value, pattern));
                } else {
                    data.put(key, value);
                }
            } else {
                data.put(key, null);
            }
        });
        return data;
    }
}
