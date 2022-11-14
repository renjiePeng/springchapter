package org.smart4j.chapter2.service;

import org.smart4j.chapter2.helper.DataBaseHelper;
import org.smart4j.chapter2.pojo.entity.Customer;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: org.smart4j.chapter2.service
 * @Author 彭仁杰
 * @Date 2022/11/10 21:50
 * @Description 提供客户数据服务
 **/
public class CustomerService {

    /**
     * 获取客户列表
     *
     * @return List<Customer>
     */
    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        return DataBaseHelper.queryEntityList(Customer.class,sql);
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
       return DataBaseHelper.insertEntity(Customer.class,fieldMap);
    }

    /**
     * 更新客户
     *
     * @param id       用户id
     * @param fieldMap 用户信息
     * @return boolean
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DataBaseHelper.updateEntity(Customer.class,id,fieldMap);
    }

    /**
     * 删除客户
     *
     * @param id 用户id
     * @return boolean
     */
    public boolean deleteCustomer(long id) {
        return DataBaseHelper.dleteEntity(Customer.class, id);
    }
}
