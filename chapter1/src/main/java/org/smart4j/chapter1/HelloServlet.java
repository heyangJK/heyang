package org.smart4j.chapter1;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")  //表示访问该servlet的 url 映射（地址）（此处为相对路径，即 “项目名称/hello” ）。  http://localhost:8080/chapter1/hello

public class HelloServlet extends HttpServlet{
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //doGet 方法中获取当前时间， 并将其放入HttpServletRequest对象中，最后转发到/WEB-INF/jsp/hello.jsp 页面
		String currentTime = dateFormat.format(new Date());
		req.setAttribute("currentTime", currentTime);
		req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp); //浏览器获得另外一个URL所指向的资源      /chapter1/WEB-INF/jsp/hello.jsp    /项目名称 （即/chapter1后）默认已经 进入了 /webapp
	}

}
/*浏览器去重新发出对另外一个URL的访问请求    “浏览器”找张三（项目名称/hello）借钱   ，李四（/WEB-INF/jsp/hello.jsp）
request.getRequestDispatcher(path).forward(request, response)   “浏览器”找张三借钱 ，张三背地里找李四借了点钱后把钱给了“浏览器”，
浏览器只知道钱来自于张三不知道李四的存在 。方法的请求转发过程结束后，浏览器地址栏保持初始的URL地址不变。
response.sendRedirect   “浏览器”找张三借钱，张三让浏找李四借 ，最后在李四那借到了钱。浏览器地址栏中显示的URL会发生改变，由初始的URL地址变成重定向的目标URL。

RequestDispatcher.forward方法的调用者与被调用者之间共享相同的request对象和response对象，它们属于同一个访问请求和响应过程 ；（因为URL地址不变）
而HttpServletResponse.sendRedirect方法调用者与被调用者使用各自的request对象和response对象，它们属于两个独立的访问请求和响应过程 。

对于同一个WEB应用程序的内部资源之间的跳转，特别是跳转之前要对请求进行一些前期预处理，并要使用HttpServletRequest.setAttribute方法传递预处理结果，那就应该使用 RequestDispatcher.forward方法。（因为属于同一个请求和响应，所以setAttribute有效）
不同WEB应用程序之间的重定向，特别是要重定向到另外一个WEB站点上的资源的情况，都应该使用HttpServletResponse.sendRedirect方法。

request.getRequestDispatcher(path).forward(request, response) 在执行完该方法的时候再进行对request的操作已经没有任何意义,如果在该方法之后再进行request.setAttribute(),该值将不会被放进当前请求的request中。
response.sendRedirect:该方法执行之后,接下来的方法也会被执行.但是使用该方法的时候,会发送一个全新的request,将不再使用原先的request,因此不论在该方法执行之前,还是在该方法执行之后,对request操作,都是无效的。

*/