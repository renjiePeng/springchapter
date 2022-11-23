package org.smart.framework.annotation;

import java.lang.annotation.*;

/**
 * @PackageName: org.smart.framework.annotation
 * @Author 彭仁杰
 * @Date 2022/11/22 15:58
 * @Description 切面注解
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
