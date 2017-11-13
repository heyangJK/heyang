package org.smart4j.chapter2.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.util.CollectionUtil;
import org.smart4j.chapter2.util.PropsUtil;

public final class DatabaseHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	private static final ThreadLocal<Connection> CONNECTION_HOLDER ;
	private static final QueryRunner QUERY_RUNNER ;
	private static final BasicDataSource DATA_SOURCE;
	
	static{
		 CONNECTION_HOLDER = new ThreadLocal<Connection>();
		 QUERY_RUNNER = new QueryRunner();
		 
		 Properties conf = PropsUtil.loadProps("config.properties");
		 String driver = conf.getProperty("jdbc.driver");
		 String url = conf.getProperty("jdbc.url");
		 String username = conf.getProperty("jdbc.username");
		 String password = conf.getProperty("jdbc.password");
		 
		 DATA_SOURCE = new BasicDataSource();
		 DATA_SOURCE.setDriverClassName(driver);
		 DATA_SOURCE.setUrl(url);
		 DATA_SOURCE.setUsername(username);
		 DATA_SOURCE.setPassword(password);
		
		
	}
	
	/**
	 * 获取数据库连接
	 */
	public static Connection getConnection(){
		Connection conn =CONNECTION_HOLDER.get();
		if(conn == null){
		try{
			conn = DATA_SOURCE.getConnection();
//			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);   //创建连接
		}catch (SQLException e){
			LOGGER.error("get connection failure",e);
			throw new RuntimeException(e);
		} finally {
		CONNECTION_HOLDER.set(conn);
		}
		}
		return conn;
	}
	
	/**
	 * 关闭数据库连接
	 */
	/*public static void closeConnection(){
		Connection conn =CONNECTION_HOLDER.get();
		if( conn !=null){
		try{
			conn.close();
		}catch (SQLException e){
			LOGGER.error("close connection failure",e);
			throw new RuntimeException(e);
		}finally {
			CONNECTION_HOLDER.remove();
		}
	}
	}*/
	
	/**
	 * 查询实体列表
	 */
	public static <T> List<T> queryEntityList(Class<T> entityClass,String sql,Object...params){
		List<T> entityList;
		try{
			Connection conn = getConnection();
			entityList = QUERY_RUNNER.query(conn,sql,new BeanListHandler<T>(entityClass),params);  //返回list对象
		}catch (SQLException e){
			LOGGER.error("query entity list failure" , e);
			throw new RuntimeException(e);
		}/*finally{
			closeConnection();
		}*/
		return entityList;
	}
	
	/**
	 * 获取客户列表
	 */
public static List<Customer> getCustomerList(){
		String sql = "SELECT * FROM customer";
			return DatabaseHelper.queryEntityList(Customer.class, sql);
		}
	

public static <T> T queryEntity(Class<T> entityClass,String sql,Object...params){
	T entity;
	try{
		Connection conn = getConnection();
		entity = QUERY_RUNNER.query(conn,sql,new BeanHandler<T>(entityClass),params);  //返回Bean对象
	}catch (SQLException e){
		LOGGER.error("query entity list failure" , e);
		throw new RuntimeException(e);
	}/*finally{
		closeConnection();
	}*/
	return entity;
}

public static Customer getCustomer(long id){
	String sql = "SELECT * FROM customer" +" WHERE id="+id;
		return DatabaseHelper.queryEntity(Customer.class, sql);
	}

/*
 * 执行查询语句  Map表示列名与列值的映射
 */
public static List<Map<String , Object>> executeQuery(String sql , Object...params){
	List<Map<String , Object>> result;
	try{
		Connection conn = getConnection();
		result = QUERY_RUNNER.query(conn, sql,new MapListHandler() , params);
	}catch (Exception e){
		LOGGER.error("execute query failure",e);
		throw new RuntimeException(e);
	}/*finally{
		closeConnection();
	}*/
	return result;
}

/**
 * 执行更新语句  返回更新了多少条记录 通用方法
 */
public static int executeUpdate(String sql,Object...params){
	int rows = 0;
	try {
		Connection conn = getConnection();
		rows = QUERY_RUNNER.update(conn,sql,params);
	}catch (SQLException e){
		LOGGER.error("execute update failure",e);
		throw new RuntimeException(e);
	}/*finally{
		closeConnection();
	}*/
	return rows;
}



/**
 * 根据更新语句  三种具体的更新操作
 * 1.插入实体
 */
public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
    if (CollectionUtil.isEmpty(fieldMap)) {
        LOGGER.error("can not insert entity: fieldMap is empty");
        return false;
    }
    String sql = "INSERT INTO " +getTableName(entityClass);
    StringBuilder columns = new StringBuilder("(");
    StringBuilder values = new StringBuilder("(");

    // 插入实体的字段名，和字段值的占位符
    for (String fieldName : fieldMap.keySet()) {
        columns.append(fieldName).append(", ");
        values.append("?, ");
    }
    columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
    values.replace(values.lastIndexOf(", "), values.length(), ")");
    sql += columns + " VALUES " + values;

    // 插入实体的值
    Object[] params = fieldMap.values().toArray();  //数组类型
    return executeUpdate(sql, params) == 1;
}

/**
 * 2.更新实体
 */
public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
    if (CollectionUtil.isEmpty(fieldMap)) {
        LOGGER.error("can not update entity: fieldMap is empty");
        return false;
    }
    // 更具 fieldMap 拼接出更新 SQL 语句
    String sql = "UPDATE " + getTableName(entityClass) + " SET ";
    StringBuilder columns = new StringBuilder();
    // 更新实体的字段
    for (String fieldName : fieldMap.keySet()) {
        columns.append(fieldName).append("=?, ");
    }

    // 去掉 SQL 最后一个 ', '
    sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id=?";

    // 更新实体的值
    List<Object> paramList = new ArrayList<Object>();
    paramList.addAll(fieldMap.values());
    paramList.add(id); // 增加主键 id
    Object[] params = paramList.toArray();  //数组类型

    return executeUpdate(sql, params) == 1;
}

/**
 * 3.删除实体
 */
public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
    String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id=?";
    return executeUpdate(sql, id) == 1;
}
/**
 * 获取操作表的表名，即实体的类名
 */
private static String getTableName(Class<?> entityClass) {
    return entityClass.getSimpleName();
}
/*
 * 执行SQL文件
 */
public static void executeSqlFile(String filePath){
	InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);  //读取文件
	BufferedReader reader = new BufferedReader(new InputStreamReader(is));  
	try{
		String sql;
     	while ((sql = reader.readLine()) != null){
		 executeUpdate(sql);  //执行文件中每条语句
	}
	}catch (Exception e){
		LOGGER.error("execute sql file failure",e);
		throw new RuntimeException(e);
	}
}

}



	

