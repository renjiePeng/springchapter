package org.smart.framework;

import org.smart.framework.helper.BeanHelper;
import org.smart.framework.helper.ClassHelper;
import org.smart.framework.helper.ControllerHelper;
import org.smart.framework.helper.IocHelper;
import org.smart.framework.util.ClassUtil;

/**
 * @PackageName: org.smart.framework
 * @Author 彭仁杰
 * @Date 2022/11/21 14:20
 * @Description 加载相应的Helper类
 **/
public class HelperLoader {

    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
