package org.smart4j.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.smart4j.chapter2.util.CollectionUtil;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.ReflectionUtil;

/*
 * 依赖注入助手类
 */
public final class IocHelper {
	static{
		Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
		if(CollectionUtil.isNotEmpty(beanMap)){
			for (Map.Entry<Class<?>,Object>beanEntry :beanMap.entrySet()){
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				//获取Bean类定义的所有成员变量（简称Bean Filed）
				Field[] beanFields = beanClass.getDeclaredFields();
				if(ArrayUtil.isNotEmpty(beanFields)){
					for (Field beanField : beanFields){
						//判断当前Bean Field是否带有Inject注解  控制器类可以用Inject，将服务类依赖注入进来
						if(beanField.isAnnotationPresent(Inject.class)){
							//在Bean Map中获取Bean Field对应的实例
							Class<?> beanFieldClass = beanField.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if(beanFieldInstance != null){
								//通过反射初始化BeanField的值
								ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);//(Object obj, Field field, Object value)
							}
						}
					}
				}
			}
		}
	}

}
