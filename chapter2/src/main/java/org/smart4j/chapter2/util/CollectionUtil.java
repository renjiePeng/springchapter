package org.smart4j.chapter2.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @PackageName: org.smart4j.chapter2.util
 * @Author 彭仁杰
 * @Date 2022/11/10 23:14
 * @Description
 **/
public final class CollectionUtil {
    /**
     * 判断集合是否为空
     * @param collection 集合
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断集合是否为非空
     * @param collection 集合
     * @return boolean
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }

    /**
     * 判断集合是否为空
     * @param map map
     * @return boolean
     */
    public static boolean isEmpty(Map<?,?> map){
        return MapUtils.isEmpty(map);
    }

    /**
     * 判断集合是否为非空
     * @param map map
     * @return boolean
     */
    public static boolean isNotEmpty(Map<?,?> map){
        return !isEmpty(map);
    }
}
