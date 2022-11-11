package org.smart4j.chapter2.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PackageName: org.smart4j.chapter2.controller
 * @Author 彭仁杰
 * @Date 2022/11/10 21:46
 * @Description
 **/
@WebServlet("customer_create")
public class CustomerCreateServlet extends HttpServlet {

    /**
     * 进入创建用户界面
     * @param req 请求
     * @param resp 响应
     * @throws ServletException 容器异常
     * @throws IOException io异常
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     * 处理创建客户请求
     * @param req 请求
     * @param resp 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
