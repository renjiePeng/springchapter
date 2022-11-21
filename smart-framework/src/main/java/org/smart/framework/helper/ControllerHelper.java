package org.smart.framework.helper;

import org.smart.framework.annotation.Action;
import org.smart.framework.bean.Handler;
import org.smart.framework.bean.Request;
import org.smart.framework.util.ArrayUtil;
import org.smart.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @PackageName: org.smart.framework.helper
 * @Author 彭仁杰
 * @Date 2022/11/21 14:07
 * @Description 控制器助手类
 **/
public final class ControllerHelper {
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();
    static {
        //获取所有Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtil.isNotEmpty(controllerClassSet)){
            //遍历所有的controller类
            for (Class<?> controllerClass : controllerClassSet) {
                //获取Controller类中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if(ArrayUtil.isNotEmpty(methods)){
                    //遍历这些Controller类中方法
                    for (Method method : methods) {
                        //判断当前方法中是否带有Action注解
                        Action action = method.getAnnotation(Action.class);
                        //验证URL映射规则
                        String mapping = action.value();
                        if(mapping.matches("\\w+:/\\w*")){
                            String[] array = mapping.split(":");
                            if(ArrayUtil.isNotEmpty(array) && array.length==2){
                                //获取请求方法与请求路径
                                String requestMethod = array[0];
                                String requestPath = array[1];
                                Request request = new Request(requestMethod, requestPath);
                                Handler handler = new Handler(controllerClass, method);
                                //初始化ActionMap
                                ACTION_MAP.put(request,handler);
                            }
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMethod, String requestPath){
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
