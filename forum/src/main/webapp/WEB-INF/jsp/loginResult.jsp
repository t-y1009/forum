<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>    
<% User user = (User)session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/forum/css/login_out_result.css">
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<div class="container">
		<% if(user != null) {%>
			<div class="succes_msg">
				<div class="icon">
					<p><img src="/forum/img/check.png"><p>
				</div>
				<p>ようこそ<span><%= user.getName() %></span>さん</p>
				<a href="/forum/Main">掲示板投稿・閲覧へ</a>
			</div>
		<% }else if (user == null){ %> 
			<div class="fail_msg">
				<div class="icon"><img src="/forum/img/ng.png"></div>
					<p>ログインに失敗しました</p>
					<a href="/forum/index.jsp">TOPへ</a>
			</div>
		<% } %>
			<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>