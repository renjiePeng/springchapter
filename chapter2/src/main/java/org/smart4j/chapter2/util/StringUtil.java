package org.smart4j.chapter2.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @PackageName: org.smart4j.chapter2.util
 * @Author 彭仁杰
 * @Date 2022/11/10 23:11
 * @Description
 **/
public final class StringUtil {

    /**
     * 判断字符串是否为空
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmpty(String str){
        if(Objects.nonNull(str)){
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否为非空
     * @param str 字符串
     * @return boolean
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
