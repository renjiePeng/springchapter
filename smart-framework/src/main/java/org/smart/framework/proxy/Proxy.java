package org.smart.framework.proxy;

/**
 * @PackageName: org.smart.framework.proxy
 * @Author 彭仁杰
 * @Date 2022/11/22 16:01
 * @Description 代理接口
 **/
public interface Proxy {
    Object doProxy(ProxyChian proxyChian) throws Throwable;
}
