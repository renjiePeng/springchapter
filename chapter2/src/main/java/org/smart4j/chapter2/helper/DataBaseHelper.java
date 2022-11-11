package org.smart4j.chapter2.helper;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.util.PropsUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @PackageName: org.smart4j.chapter2.helper
 * @Author 彭仁杰
 * @Date 2022/11/10 23:34
 * @Description
 **/
public class DataBaseHelper {
    private static final Logger logger= LoggerFactory.getLogger(DataBaseHelper.class);

    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private static String DRIVER = "";

    private static String URL = "";

    private static String USERNAME = "";

    private static String PASSWORD = "";

    static {
        Properties conf = PropsUtil.loadProp("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("cat not load jdbc driver",e);
        }
    }

    /**
     * 获取数据库连接
     * @return Connection
     */
    public static Connection getConnection(){
        Connection connection = CONNECTION_HOLDER.get();
        if(Objects.isNull(connection)){
            try {
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            } catch (SQLException throwables) {
                logger.error("get connection failure",throwables);
                throw new RuntimeException(throwables);
            }finally {
                CONNECTION_HOLDER.set(connection);
            }
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     * @return Connection
     */
    public static Connection closeConnection(){
        Connection connection = CONNECTION_HOLDER.get();
        if(Objects.nonNull(connection)){
            try {
                connection.close();
            } catch (SQLException throwables) {
                logger.error("close connection failure",throwables);
                throw new RuntimeException(throwables);
            }finally {
                CONNECTION_HOLDER.remove();
            }
        }
        return connection;
    }

    /**查询实体列表
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params){
        List<T> entityList;
        Connection connection = getConnection();
        try {
            entityList = QUERY_RUNNER.query(connection,sql, new BeanListHandler<T>(entityClass),params);
        } catch (SQLException throwables) {
            logger.error("query entity list failure", throwables);
            throw new RuntimeException(throwables);
        }finally {
            closeConnection();
        }
        return entityList;
    }

    /**
     * 查询单个实体类
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params){
        T entity;
        Connection connection = getConnection();
        try {
            entity = QUERY_RUNNER.query(connection,sql, new BeanHandler<T>(entityClass),params);
        } catch (SQLException throwables) {
            logger.error("query entity list failure", throwables);
            throw new RuntimeException(throwables);
        }finally {
            closeConnection();
        }
        return entity;
    }

    /**+
     * 执行查询语句
     * @param sql sql语句
     * @param params 参数
     * @return  List<Map<String,Object>>
     */
    public static List<Map<String,Object>> executeQuery(String sql, Object... params){
        List<Map<String,Object>> result;
        Connection connection = getConnection();
        try {
            result = QUERY_RUNNER.query(connection,sql, new MapListHandler(),params);
        } catch (SQLException throwables) {
            logger.error("query entity list failure", throwables);
            throw new RuntimeException(throwables);
        }finally {
            closeConnection();
        }
        return result;
    }
}
