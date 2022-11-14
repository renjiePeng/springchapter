package org.smart4j.chapter2.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.util.CollectionUtil;
import org.smart4j.chapter2.util.PropsUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * @PackageName: org.smart4j.chapter2.helper
 * @Author 彭仁杰
 * @Date 2022/11/10 23:34
 * @Description
 **/
public final class DataBaseHelper {

    public DataBaseHelper() {
    }

    private static final Logger logger= LoggerFactory.getLogger(DataBaseHelper.class);

    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private static final BasicDataSource DATA_SOURCE = new BasicDataSource();

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

        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERNAME);
        DATA_SOURCE.setPassword(PASSWORD);
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
                connection = DATA_SOURCE.getConnection();
            } catch (SQLException throwables) {
                logger.error("get connection failure",throwables);
                throw new RuntimeException(throwables);
            }finally {
                logger.info("连接----当前线程id：{},name:{}",Thread.currentThread().getId(),Thread.currentThread().getName());
                CONNECTION_HOLDER.set(connection);
            }
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     * @return Connection
     */
//    public static Connection closeConnection(){
//        Connection connection = CONNECTION_HOLDER.get();
//        if(Objects.nonNull(connection)){
//            try {
//                connection.close();
//            } catch (SQLException throwables) {
//                logger.error("close connection failure",throwables);
//                throw new RuntimeException(throwables);
//            }finally {
//                logger.info("断开---当前线程id：{},name:{}",Thread.currentThread().getId(),Thread.currentThread().getName());
//                CONNECTION_HOLDER.remove();
//            }
//        }
//        return connection;
//    }

    /**查询实体列表
     * @param entityClass 实体类
     * @param sql sql语句
     * @param params 参数
     * @param <T> 泛型
     * @return List<T>
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params){
        List<T> entityList;
        Connection connection = getConnection();
        try {
            entityList = QUERY_RUNNER.query(connection,sql, new BeanListHandler<T>(entityClass),params);
        } catch (SQLException throwables) {
            logger.error("query entity list failure", throwables);
            throw new RuntimeException(throwables);
        }
        return entityList;
    }

    /**
     * 查询单个实体类
     * @param entityClass 实体类
     * @param sql sql语句
     * @param params 参数
     * @param <T> 泛型
     * @return T
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params){
        T entity;
        Connection connection = getConnection();
        try {
            entity = QUERY_RUNNER.query(connection,sql, new BeanHandler<T>(entityClass),params);
        } catch (SQLException throwables) {
            logger.error("query entity list failure", throwables);
            throw new RuntimeException(throwables);
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
        }
        return result;
    }

    /**
     * 执行更新语句
     * @param sql sql语句
     * @param params 参数
     * @return int
     */
    public static int executeUpdate(String sql,Object... params){
        int rows = 0;
        try{
            Connection connection = getConnection();
            rows = QUERY_RUNNER.update(connection,sql,params);
        }catch (Exception e){
            logger.error("execute update failure" , e);
            throw new RuntimeException(e);
        }
        return rows;
    }

    /**
     * 插入实体
     * @param entityClass 实体类
     * @param fieldMap 内容
     * @return int
     */
    public static <T> boolean insertEntity(Class<T> entityClass,Map<String,Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            logger.error("can not insert entity: fieldMap is empty");
            return false;
        }

        String sql = "INSERT INTO " + getTableName(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "),columns.length(),")");
        values.replace(values.lastIndexOf(", "),values.length(),")");
        sql+=columns+" VALUES " +values;

        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql,params) == 1;
    }

    public static <T> boolean updateEntity(Class<T> entityClass,long id,Map<String,Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            logger.error("can not update entity: fieldMap is empty");
            return false;
        }
        String sql = "UPDATE " + getTableName(entityClass) + " SET ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append("=?, ");
        }

        sql+=columns.substring(0,columns.lastIndexOf(", "))+ " WHERE id =?";
        List<Object> paramList = new ArrayList<>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();
        return executeUpdate(sql,params) ==1;
    }

    public static <T> boolean dleteEntity(Class<T> entityClass,long id){
        String sql = "DELETE FROM "+ getTableName(entityClass)+" WHERE id =?";
        return executeUpdate(sql,id)==1;
    }

    private static String getTableName (Class<?> entityClass){
        return entityClass.getSimpleName();
    }
}
