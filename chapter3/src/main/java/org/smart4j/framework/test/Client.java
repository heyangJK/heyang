package org.smart4j.framework.test;

import org.springframework.aop.framework.ProxyFactory;

public class Client {
	public static void main(String[] args){
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new HelloImpl());
		proxyFactory.addAdvice(new GreetingBeforeAdvice());
		proxyFactory.addAdvice(new GreetingAfterAdvice());
		
		Hello hello = (Hello) proxyFactory.getProxy();
		hello.say("Jack");
		
	}

}
