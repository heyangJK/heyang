package org.smart4j.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.smart4j.framework.util.ReflectionUtil;
/*
 * Bean助手类，相当于一个Bean容器，传入一个Bean类就能获取Bean实例
 */

public final class BeanHelper {
	/*
	 * 定义Bean映射（用于存放Bean类与Bean实例的映射关系）
	 */
	private static Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();
	static {
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for(Class<?> beanClass :beanClassSet){
			Object obj = ReflectionUtil.newInstance(beanClass);
			BEAN_MAP.put(beanClass, obj);
		}
	}
	
	/*
	 * 获取Bean映射
	 */
	public static Map<Class<?>,Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/*
	 * 获取Bean 实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls){
		if(!BEAN_MAP.containsKey(cls)){
			throw new RuntimeException("can not get bean by class:"+cls);
		}
		return (T) BEAN_MAP.get(cls);
	}
		

}
