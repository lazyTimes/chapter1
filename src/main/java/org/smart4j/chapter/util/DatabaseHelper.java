package org.smart4j.chapter.util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库工具类
 */
public class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    /**
     * dbUtil 工具类
     */
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL = new ThreadLocal<Connection>();

    /**
     * jdbc
     */
    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    /**
     * 静态初始化获取jdbc的相关内容
     */
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
            throw new RuntimeException(e);
        }

    }

    /**
     * 使用threadLocal 改写的数据库链接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if(connection == null){
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
        return connection;
    }

    /**
     * 查询的第二种方式，使用内置connection 的threadlocal进行操作
     * 查询数据列表， 使用dbutil 进行查询
     * BeanListHandler 处理bean 结果
     * @param entityClass 实体对象class
     * @param sql 查询语句
     * @param params 查询参数
     * @param <T> 泛型参数
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params){
        List<T> entityList = null;
        Connection connection = getConnection();
        try {
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass), params);
        } catch(SQLException e){
            LOGGER.error("query entity failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return entityList;
    }

    /**
     * 用于复杂查询使用，Map列名与列值成对应关系
     * @param sql 查询语句
     * @param params 参数
     * @return List<Map<String, Object>> 键值对对象集合
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params){
        List<Map<String, Object>> result;
        try {
            Connection connection = getConnection();
            result = QUERY_RUNNER.query(connection, sql, new MapListHandler(), params);
        }catch (Exception e){
            LOGGER.error("execute query failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * 唯一查询，根据主键查找实体类
     * @param entityClass 实体类
     * @param sql 数据库链接语句
     * @param params 参数
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params){
        T entity = null;
        try {
            Connection connection = getConnection();
            entity = QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass), params);
        } catch(SQLException e){
            LOGGER.error("query entity failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return entity;
    }


    /**
     * 此方式不建议使用
     * 查询数据列表， 使用dbutil 进行查询
     * BeanListHandler 处理bean 结果
     * @param connection 数据库链接
     * @param entityClass 实体对象class
     * @param sql 查询语句
     * @param params 查询参数
     * @param <T> 泛型参数
     * @return
     */
    @Deprecated
    public static <T> List<T> queryEntityList(Connection connection, Class<T> entityClass, String sql, Object... params){
        List<T> entityList = null;
        try {
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass), params);
        } catch(SQLException e){
            LOGGER.error("query entity failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return entityList;

    }

    /*
     * 获取数据库连接
     * @deprecated 此方法被弃用
     */
//    @Deprecated
//    public static Connection getConnection(){
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            LOGGER.error("connection error failure", e);
//            throw new RuntimeException(e);
//        }
//        return connection;
//    }

    /**
     * 使用threadlocal 进行关闭链接操作
     */
    public static void closeConnection(){
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("connection close failure", e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 此方法以及被后续的threadlocal 弃用
     * 关闭链接
     * @param connection 数据库链接
     * @deprecated 被新方法替代
     */
    @Deprecated
    public static void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("connection close failure", e);
                throw new RuntimeException(e);
            }
        }
    }
}
