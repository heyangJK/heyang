package org.smart4j.chapter2.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.util.PropsUtil;

import com.mysql.jdbc.Connection;

public class CustomerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	
	static{
		Properties conf = PropsUtil.loadProps("config.properties");
		DRIVER = conf.getProperty("jadc.driver");
		URL = conf.getProperty("jadc.url");
		USERNAME = conf.getProperty("jadc.username");
		PASSWORD = conf.getProperty("jadc.password");
		
		try {
			Class.forName(DRIVER);
		}catch (ClassNotFoundException e){
			LOGGER.error("can not load jdbc driver",e);
		}
	}
	
	/**
	 * 获取客户列表
	 */
	public List<Customer> getCustomerList(String keyword){
		Connection conn = null;
//		try{
//			List<Customer> customerList = new ArrayList<Customer> ();
//			
//			
//		}
		//TODO
		return null;
	}
	
	/**
	 * 获取客户
	 */
	public Customer getCustomer(long id){
		//TODO
		return null;
	}
	
	/**
	 * 创建客户
	 */
	public boolean createCustomer(Map<String,Object> fieldMap){
		//TODO
		return false;
	}
	
	/**
	 * 更新客户
	 */
	public boolean updateCustomer(long id , Map<String,Object> fieldMap){
		//TODO
		return false;
	}
	
	
	/**
	 * 删除客户
	 */
	public boolean deleteCustomer(long id){
		//TODO
		return false;
	}

} 
