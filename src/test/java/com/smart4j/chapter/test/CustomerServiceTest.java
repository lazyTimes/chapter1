package com.smart4j.chapter.test;

import com.smart4j.chapter.model.Customer;
import com.smart4j.chapter.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单元测试
 */
public class CustomerServiceTest {

    private final CustomerService customerService;


    public CustomerServiceTest(CustomerService customerService) {
        this.customerService = customerService;
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
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomer() {
        Map<String, Object> filed = new HashMap<String, Object>();
        filed.put("name", "123");
        filed.put("contact", "jack");
        filed.put("telephone", "1394456789");
        customerService.createCustomer(filed);
    }

    @Test
    public void editCustomer() {
    }

    @Test
    public void deleteCustomer() {
    }

}
