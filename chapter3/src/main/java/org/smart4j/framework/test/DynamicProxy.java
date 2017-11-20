package org.smart4j.framework.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler{
	private Object target;
	public DynamicProxy(Object target){
		this.target = target;
	}
	@Override
	public Object invoke(Object proxy,Method method,Object[] args) throws Throwable{
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
	}
	@SuppressWarnings("unchecked")
	public <T> T getProxy(){
		return (T) Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				this //JDK动态代理
				);
	}
	private void before(){
		System.out.println("Before1");
	}
	private void after(){
		System.out.println("After2");
	}
	public static void main(String[] args){
       //测试JDK实现的动态代理  
       /* Hello hello = new HelloImp(); 
        DynamicProxy dynamicProxy = new DynamicProxy(hello); 
        Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(), 
        hello.getClass().getInterfaces(), 
        dynamicProxy
        ); 
        helloProxy.sayHello("jack");*/  
		Hello hello = new HelloImpl();
		DynamicProxy dynamicProxy = new DynamicProxy(hello);
		Hello helloProxy = dynamicProxy.getProxy();
		helloProxy.say("Jack");
	}


}
