package chapter2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;

/**
 * 
 * 
 * 构建组：大道金服科技部
 * 作者:heyang
 * 邮箱:heyang@ddjf.com.cn
 * 日期:2017年11月8日下午3:27:37
 * 功能说明：CustomerServiceTest 单元测试
 *
 */
public class CustomerServiceTest {
	private final CustomerService customerService;
	
	public CustomerServiceTest(){
		customerService = new CustomerService();
	}
	
	@Before  //初始化方法   对于每一个测试方法都要执行一次（注意与BeforeClass区别，后者是对于所有方法执行一次）
	public void init(){
		//TODO初始化数据库
	}
	
	@Test
	public void getCustomerListTest() throws Exception{
		List<Customer> customerList = customerService.getCustomerList(null);
		Assert.assertEquals(2, customerList.size());
		
	}
	
	@Test
	public void getCustomerTest() throws Exception{
		long id = 1;
		Customer customer = customerService.getCustomer(id);
		Assert.assertNotNull(customer);
	}
	
	@Test
	public void updateCustomerTest() throws Exception{
		long id = 1;
		Map<String , Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("contact","Eric");
		boolean result = customerService.updateCustomer(id, fieldMap);
		Assert.assertTrue(result);
	}
	
	@Test
	public void deleteCustomerTest() throws Exception{
		long id=1;
		boolean result = customerService.deleteCustomer(id);
		Assert.assertTrue(result);
	}
	

}
