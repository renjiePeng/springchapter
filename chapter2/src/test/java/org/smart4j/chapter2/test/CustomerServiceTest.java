package org.smart4j.chapter2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter2.pojo.entity.Customer;
import org.smart4j.chapter2.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

/**
 * @PackageName: org.smart4j.chapter2.test
 * @Author 彭仁杰
 * @Date 2022/11/10 21:56
 * @Description
 **/
public class CustomerServiceTest {

    private final CustomerService customerService;

    public CustomerServiceTest() {
        this.customerService = new CustomerService();
    }

    @Before
    public void init(){
        //TODO 初始化数据库
    }

    @Test
    public void getCustomerListTest() throws Exception{
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2, customerList.size());
    }

    @Test
    public void getCustomerTest() throws Exception{
        long id =1;
        Customer customer= customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomerTest()throws Exception{
        HashMap<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name","customer100");
        fieldMap.put("contact","John");
        fieldMap.put("telephone","13512345678");
        boolean customer = customerService.createCustomer(fieldMap);
        Assert.assertTrue(customer);
    }

    @Test
    public void updateCustomerTest()throws Exception{
        long id = 1;
        HashMap<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("contact","Eric");
        boolean result = customerService.updateCustomer(id,fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomerTest()throws Exception{
        long id = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }

    public <T> String test1(Supplier<T> supplier){
        T t = supplier.get();
        return t.toString();
    }

    @Test
    public void test2(){
        System.out.println(test1(Customer::new));
    }

    public <T> String test3(Class<T> tClass){
        try {
            return Class.forName(tClass.getName()).toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Test
    public void test4(){
        System.out.println(test1(Customer::new));
    }
}
