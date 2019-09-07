package org.smart4j.chapter.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter.model.Customer;
import org.smart4j.chapter.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单元测试
 */

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
    public void getCustomerList() {
        List<Customer> customerList = customerService.getCustomerList(null);
        Assert.assertEquals(2, customerList.size());
    }

    @Test
    public void getCustomer() {
        int id = 1;
        Customer customer = customerService.getCustomer(id);
        System.err.println(customer);
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomer() {
        Map<String, Object> filed = new HashMap<String, Object>();
        filed.put("name", "123");
        filed.put("contact", "jack");
        filed.put("remark", "1394456789");
        boolean customer = customerService.createCustomer(filed);
        Assert.assertTrue(customer);

    }

    @Test
    public void editCustomer() {
        int id = 1;
        Map<String, Object> filed = new HashMap<String, Object>();
        filed.put("name", "123666asdsad");
        boolean editCustomer = customerService.editCustomer(id, filed);
        Assert.assertTrue(editCustomer);

    }

    @Test
    public void deleteCustomer() {
        int id = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }

}
