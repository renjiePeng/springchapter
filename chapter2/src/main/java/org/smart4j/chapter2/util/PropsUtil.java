package org.smart4j.chapter2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * @PackageName: org.smart4j.chapter2.util
 * @Author 彭仁杰
 * @Date 2022/11/10 22:42
 * @Description 属性文件工具类
 **/
public final class PropsUtil {
    private static final Logger logger= LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     * @param fileName 文件名称
     * @return Properties
     */
    public static Properties loadProp(String fileName){
        Properties properties = null;
        InputStream inputStream = null;
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(Objects.isNull(inputStream)){
                throw new FileNotFoundException(fileName+"file is not find");
            }

            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            logger.error("load properties file  failure",e);
            e.printStackTrace();
        }finally {
            if(Objects.nonNull(inputStream)){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("close input stream failure", e);
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    /**
     * 获取字符型属性（默认值为空字符串）
     * @param properties 属性
     * @param key 关键字
     * @return String
     */
    public static String getString(Properties properties, String key){
        return getString(properties,key,"");
    }

    /**
     * 获取字符型属性（可指定默认值）
     * @param properties 属性
     * @param key 关键字
     * @param defaultValue 默认值
     * @return String
     */
    public static String getString(Properties properties, String key, String defaultValue){
        String value = defaultValue;
        if(properties.containsKey(key)){
            value = properties.getProperty(key);
        }
        return value;
    }

    /**
     * 获取数值型属性（默认值0）
     * @param properties 属性
     * @param key 关键字
     * @return int
     */
    public static int getInt(Properties properties,String key){
        return getInt(properties,key,0);
    }

    /**
     * 获取数值型属性
     * @param properties 属性
     * @param key 关键字
     * @param defaultValue 默认值
     * @return int
     */
    public static int getInt(Properties properties,String key,int defaultValue){
        int value = defaultValue;
        if(properties.containsKey(key)){
            value = CastUtil.castInt(properties.getProperty(key));
        }
        return value;
    }

    /**
     * 获取布尔型属性（默认值false）
     * @param properties 属性
     * @param key 关键字
     * @return int
     */
    public static boolean getBoolean(Properties properties,String key){
        return getBoolean(properties,key,false);
    }

    /**
     * 获取布尔型属性
     * @param properties 属性
     * @param key 关键字
     * @param defaultValue 默认值
     * @return int
     */
    public static boolean getBoolean(Properties properties,String key,boolean defaultValue){
        boolean value = defaultValue;
        if(properties.containsKey(key)){
            value = CastUtil.castBoolean(properties.getProperty(key));
        }
        return value;
    }
}
