<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head><%@ include file="public/head.jspf" %>
</head>
	<body>
		<a href="send_main_aindex.action">直接到后台页面UI版本</a>
		<a href="send_main_index.action">直接到后台页面frameset div版本</a>
		<a href="${shop}/category_update.action?id=1&type=testupdate&hot=true">访问update</a>
		<a href="${shop}/category_save.action">访问save</a>
		<a href="category_query.action">查询所有类别</a>
		<a href="account_update.action?id=1">ModelDriven(update account)测试</a>
		<a href="category_query.action">ModelDriven(category)测试</a>
		<a href="${shop}/account_query.action">ModelDriven(account)测试</a>
		<br />
		<!--  
		<c:forEach items="${requestScope.categoryList }" var="category">
			${category.id }|${category.type }|${category.hot } <br />
		</c:forEach>
		<hr />
		<c:forEach items="${sessionScope.categoryList }" var="category">
			${category.id }|${category.type }|${category.hot } <br />
		</c:forEach>
		<hr />
		<c:forEach items="${applicationScope.categoryList }" var="category">
			${category.id }|${category.type }|${category.hot } <br />
		</c:forEach>
		-->

		<c:forEach items="${requestScope.accountList }" var="account">
			${account.login }|${account.name }|${account.pass } <br />
		</c:forEach>
		<c:forEach items="${sessionScope.accountList }" var="account">
			${account.login }|${account.name }|${account.pass } <br />
		</c:forEach>
		<c:forEach items="${applicationScope.accountList }" var="account">
			${account.login }|${account.name }|${account.pass } <br />
		</c:forEach>
	</body>
  
</html>
