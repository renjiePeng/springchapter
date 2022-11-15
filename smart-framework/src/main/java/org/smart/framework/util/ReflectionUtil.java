package org.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @PackageName: org.smart.framework.util
 * @Author 彭仁杰
 * @Date 2022/11/15 23:12
 * @Description
 **/
public final class ReflectionUtil {
    private static final Logger logger= LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     * @param cls class信息
     * @return Object
     */
    public static Object newInstance(Class<?> cls){
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            logger.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     * @param obj
     * @param method 方法
     * @param args 参数
     * @return
     */
    public static Object invokeMethod(Object obj, Method method,Object... args){
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj,args);
        } catch (Exception e) {
            logger.error("invoke method failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (Exception e) {
            logger.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }
}
