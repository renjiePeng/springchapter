package org.smart4j.chapter2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.helper.DataBaseHelper;
import org.smart4j.chapter2.pojo.entity.Customer;
import org.smart4j.chapter2.util.PropsUtil;

import java.sql.*;
import java.util.*;

/**
 * @PackageName: org.smart4j.chapter2.service
 * @Author 彭仁杰
 * @Date 2022/11/10 21:50
 * @Description 提供客户数据服务
 **/
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);


    /**
     * 获取客户列表
     *
     * @return List<Customer>
     */
    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        try {
            return DataBaseHelper.queryEntityList(Customer.class,sql);
        } finally {
            DataBaseHelper.closeConnection();
        }
    }

    /**
     * 获取客户
     *
     * @param id 用户id
     * @return Customer
     */
    public Customer getCustomer(long id) {
        String sql = "SELECT * from customer where id="+id;
        return DataBaseHelper.queryEntity(Customer.class,sql);
    }

    /**
     * 创建客户
     *
     * @param fieldMap 客户信息
     * @return boolean
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return false;
    }

    /**
     * 更新客户
     *
     * @param id       用户id
     * @param fieldMap 用户信息
     * @return boolean
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return false;
    }

    /**
     * 删除客户
     *
     * @param id 用户id
     * @return boolean
     */
    public boolean deleteCustomer(long id) {
        return false;
    }
}
