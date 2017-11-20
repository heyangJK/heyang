package org.smart4j.framework.test;

public class HelloImpl implements Hello{
	@Override
	public void say(String name){
		System.out.println("Hello " + name);
	}

}
