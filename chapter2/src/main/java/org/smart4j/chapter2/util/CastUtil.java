package org.smart4j.chapter2.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @PackageName: org.smart4j.chapter2.util
 * @Author 彭仁杰
 * @Date 2022/11/10 22:59
 * @Description
 **/
public class CastUtil {

    /**
     * 转为String型
     * @param obj Object
     * @return String
     */
    public static String castString(Object obj){
        return CastUtil.castString(obj,"");
    }

    /**
     *
     * @param obj Object
     * @param defaultValue 默认值
     * @return String
     */
    public static String castString(Object obj,String defaultValue){
        return obj !=null?String.valueOf(obj):defaultValue;
    }

    /**
     * 转为Double型
     * @param obj Object
     * @return String
     */
    public static double castDouble(Object obj){
        return CastUtil.castDouble(obj,0);
    }

    /**
     *
     * @param obj Object
     * @param defaultValue 默认值
     * @return String
     */
    public static double castDouble(Object obj,double defaultValue){
        double doubleValue = 0;
        if(Objects.isNull(obj)){
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)){
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转为Long型
     * @param obj Object
     * @return String
     */
    public static double castLong(Object obj){
        return CastUtil.castLong(obj,0L);
    }

    /**
     *
     * @param obj Object
     * @param defaultValue 默认值
     * @return String
     */
    public static double castLong(Object obj,long defaultValue){
        long longValue = 0L;
        if(Objects.isNull(obj)){
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)){
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转为Int型
     * @param obj Object
     * @return String
     */
    public static int castInt(Object obj){
        return CastUtil.castInt(obj,0);
    }

    /**
     *
     * @param obj Object
     * @param defaultValue 默认值
     * @return String
     */
    public static int castInt(Object obj,int defaultValue){
        int longValue = 0;
        if(Objects.isNull(obj)){
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)){
                try {
                    longValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转为boolean型
     * @param obj Object
     * @return String
     */
    public static boolean castBoolean(Object obj){
        return CastUtil.castBoolean(obj,false);
    }

    /**
     *
     * @param obj Object
     * @param defaultValue 默认值
     * @return String
     */
    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean booleanValue = false;
        if(Objects.isNull(obj)){
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }
}
