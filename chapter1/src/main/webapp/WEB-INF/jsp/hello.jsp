<%@ page pageEncoding="UTF-8"%>
<html>
<head>
<title>Hello</title>
</head>
<body>
	<h1>Hello!</h1>
	<h2>当前时间 ：${currentTime} </h2>  <!-- //使用JSTL表达式获取从HelloServlet里传来的 currentTime请求属性 -->
</body>
</html>                                <!-- .jsp写在webapp->WEB-INF的jsp里 -->