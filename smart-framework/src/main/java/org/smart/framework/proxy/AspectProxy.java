package org.smart.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @PackageName: org.smart.framework.proxy
 * @Author 彭仁杰
 * @Date 2022/11/22 16:19
 * @Description
 **/
public class AspectProxy implements Proxy{

    private static final Logger logger= LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChian proxyChian) throws Throwable {
        Object result = null;
        Class<?> cls = proxyChian.getTargetClass();
        Method method = proxyChian.getTargetMethod();
        Object[] params = proxyChian.getMethodParams();
        begin();
        try {
            if(intercept(cls,method,params)){
                before(cls,method,params);
                result = proxyChian.doProxyChain();
                after(cls,method,params);
            }else{
                result = proxyChian.doProxyChain();
            }
        } catch (Throwable e) {
            logger.error("proxy failure",e);
            error(cls,method,params,e);
            throw  e;
        }finally {
            end();
        }
        return result;
    }

    private void end() {

    }

    private void error(Class<?> cls, Method method, Object[] params, Throwable e) {

    }

    private void after(Class<?> cls, Method method, Object[] params) {

    }

    private void before(Class<?> cls, Method method, Object[] params) {

    }

    private boolean intercept(Class<?> cls, Method method, Object[] params) {
        return true;
    }

    private void begin() {

    }
}
