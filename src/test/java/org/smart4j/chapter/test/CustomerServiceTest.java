package org.smart4j.chapter.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter.model.Customer;
import org.smart4j.chapter.service.CustomerService;
import org.smart4j.chapter.util.DatabaseHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    public void init() throws IOException {
        //TODO 初始化数据库
        String file = "sql/customer_init.sql";
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        String sql;
        while ((sql = bufferedReader.readLine()) != null){
            DatabaseHelper.executeUpdate(sql,null);
        }
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
