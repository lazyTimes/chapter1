package org.smart4j.chapter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter.model.Customer;
import org.smart4j.chapter.util.DatabaseHelper;
import org.smart4j.chapter.util.PropsUtil;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 业务层
 */
public class CustomerService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    /**
     * jdbc
     */
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties properties = PropsUtil.loadProps("config.properties");
        DRIVER = properties.getProperty("jdbc.driver");
        URL = properties.getProperty("jdbc.url");
        USERNAME = properties.getProperty("jdbc.username");
        PASSWORD = properties.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("CAN NOT LOAD jdbc driver", e);
        }

    }

    /**
     * 获取客户列表
     *
     * @param keyword 关键字
     * @return
     */
    public List<Customer> getCustomerList(String keyword) {
        String sql = "select * from customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql, null);
    }

    /**
     * 获取用户
     */
    public Customer getCustomer(int id) {
        return DatabaseHelper.queryEntity(Customer.class, "select * from customer where id=?", id);
    }

    /**
     * 创建用户
     *
     * @param filedMap 字段map
     * @return
     */
    public boolean createCustomer(Map<String, Object> filedMap) {
        return DatabaseHelper.insertEntity(Customer.class, filedMap);
    }

    /**
     * 修改用户
     *
     * @param filedMap 修改字段
     * @param id       主键
     */
    public boolean editCustomer(int id, Map<String, Object> filedMap) {
        return DatabaseHelper.updateEntity(Customer.class, id, filedMap);
    }

    /**
     * 删除用户
     */
    public boolean deleteCustomer(int id) {
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }
}
