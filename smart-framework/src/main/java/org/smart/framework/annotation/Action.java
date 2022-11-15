package org.smart.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @PackageName: org.smart.framework.annotation
 * @Author 彭仁杰
 * @Date 2022/11/15 22:46
 * @Description action注解
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    /**
     * 请求路径
     * @return String
     */
    String value();
}
