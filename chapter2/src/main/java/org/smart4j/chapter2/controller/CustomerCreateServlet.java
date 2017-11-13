package org.smart4j.chapter2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer_create")  //这个Servlet只有一个请求路径但可以同时处理两种不同的请求（doGet/doPost/doPut/doDelete）
public class CustomerCreateServlet extends HttpServlet{
	
	/**
	 * 进入 创建客户 界面
	 */
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException , IOException{
		//TODO
		
	}
	/**
	 * 处理 创建客户 请求
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException{
		
		//TODO
	}
	
	
}
//lalala