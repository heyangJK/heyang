package org.smart4j.framework.helper;

import java.util.HashSet;
import java.util.Set;

import org.smart4j.framework.ConfigHelper;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

public class ClassHelper {
	/*
	 * 定义类集合
	 */
	private static final Set<Class<?>> CLASS_SET;
	static {
		String basePackge = ConfigHelper.getAppBasePackge();
		CLASS_SET = ClassUtil.getClassSet(basePackge);
	}
	
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	
	 /**
     * 获取应用包名下所有Service类
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Service.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }
    /**
     * 获取应用包名下所有Controller类
     */
    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Controller.class)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }
    /** 
     * 获取应用包名下所有Bean类(包括Service，Controller) 
     */  
    public static Set<Class<?>> getBeanClassSet() {  
       Set<Class<?>> beanClassSet = new HashSet<Class<?>>();  
       beanClassSet.addAll(getServiceClassSet());  
       beanClassSet.addAll(getControllerClassSet());  
        return beanClassSet;  
    }  
}  
