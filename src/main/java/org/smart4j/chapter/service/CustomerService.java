package org.smart4j.chapter.service;

import org.smart4j.chapter.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * 业务层
 */
public class CustomerService {

    /**
     * 获取客户列表
     * @param keyword 关键字
     * @return
     */
    public List<Customer> getCustomerList(String keyword) {
        //TODO
        return null;
    }

    /**
     * 获取用户
     */
    public Customer getCustomer(int id) {
        //TODO
        return null;
    }

    /**
     * 创建用户
     * @param filedMap 字段map
     * @return
     */
    public boolean createCustomer(Map<String, Object> filedMap){
        //TODO
        return false;
    }

    /**
     * 修改用户
     * @param filedMap 修改字段
     * @param id 主键
     */
    public boolean editCustomer(int id, Map<String, Object> filedMap){
        // TODO
        return false;
    }

    /**
     * 删除用户
     */
    public boolean deleteCustomer(int id){
        //TODO
        return false;
    }
}
