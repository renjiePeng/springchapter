package org.smart4j.chapter2.controller;

import org.smart4j.chapter2.pojo.entity.Customer;
import org.smart4j.chapter2.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @PackageName: org.smart4j.chapter2.controller
 * @Author 彭仁杰
 * @Date 2022/11/10 21:46
 * @Description
 **/
@WebServlet("/customer")
public class CustomerCreateServlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    /**
     * 进入创建用户界面
     * @param req 请求
     * @param resp 响应
     * @throws ServletException 容器异常
     * @throws IOException io异常
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = customerService.getCustomerList();
        req.setAttribute("customerList",customerList);
        req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req,resp);
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
