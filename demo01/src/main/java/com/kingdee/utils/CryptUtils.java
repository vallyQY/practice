package com.kingdee.utils;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 功能描述: AES/DES加密解密类
 *
 * <pre>
 *  1. 加密过程
 * {@code
 *
 *   String content = "Tommy And Jessica.";
 *   // 编码用于安全传输
 *   String inputBase64 = bs64EncryptWithHead(idStr);
 *   // 加密获得密文
 *   String securityStr = aesEncrypt64(inputBase64);
 *
 *   2. 解密过程
 *
 *   // 对密文进行解密,获得base64编码字符串
 *   String base64Str = aesDecrypt64(securityStr);
 *   // 对base64编码解密获得原文
 *   String plainTxt = bs64DecryptWithHead(base64Str);
 * }
 * </pre>
 *
 * @author tommy create on 2019-08-20-15:34
 */
@Slf4j
public class CryptUtils {

    private static final int STRING_LENGTH = 8;


    /**
     * base64
     *
     * @param plainStr 原数据
     * @return 转换后数据
     */
    public static String bs64Encrypt(String plainStr) {
        return Base64.getEncoder().encodeToString(plainStr.getBytes());
    }

    /**
     * base64编码加入6位混淆
     *
     * @param plainStr 原数据
     * @return 转换后数据
     */
    public static String bs64EncryptWithHead(String plainStr) {
        if (plainStr == null || plainStr.length() == 0) {
            System.out.println("Empty plain text");
            return null;
        }
        String headStr = RandomStringUtils.randomAlphanumeric(6);
        return headStr + Base64.getEncoder().encodeToString(plainStr.getBytes());
    }

    /**
     * base64解码
     *
     * @param base64Str base64编码
     * @return 解码后数据
     */
    public static String bs64Decrypt(String base64Str) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Str);
        return new String(decodedBytes);
    }

    /**
     * 去除混淆内容后进行base64解码
     *
     * @param base64Str 包含混淆内容base64编码
     * @return 原数据
     */
    public static String bs64DecryptWithHead(String base64Str) {
        if(Strings.isNullOrEmpty(base64Str) || base64Str.length() < STRING_LENGTH){
            return base64Str;
        }
        String inputStr = base64Str.substring(6);
        byte[] decodedBytes = Base64.getDecoder().decode(inputStr);
        return new String(decodedBytes);
    }

    /**
     * 获得秘密密钥
     *
     * @param secretKey 密钥
     * @return 密钥KEY
     * @throws NoSuchAlgorithmException E
     */
    private static SecretKey generateKey(String secretKey, String algorithm) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom(secretKey.getBytes());

        // 为我们选择的DES算法生成一个KeyGenerator对象
        KeyGenerator kg = KeyGenerator.getInstance(algorithm);
        kg.init(secureRandom);

        // 生成密钥
        return kg.generateKey();
    }

    private static final String SECRET_KEY = "xzkingdee@123-=.";
}
