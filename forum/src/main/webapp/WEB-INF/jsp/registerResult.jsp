<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% boolean result = (boolean)request.getAttribute("result"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/forum/css/register.css">
<meta charset="UTF-8">
<title>登録結果</title>
</head>
<body>
	<div class="container">
		<% if(result){ %>>
			<div class="result_succes_msg">
				<div class="icon"><img src="/forum/img/register.png"></div>
					<p>ユーザー登録が完了しました</p>
				<a href="/forum/">TOPへ</a>
			</div>
		<% }else{ %>
			<div class="result_fail_msg">
				<div class="icon"><img src="/forum/img/ng.png"></div>
					<p>既に登録されているユーザー又は入力に不備があった為、失敗しました</p>
					<p>もう一度やり直してください</p>
				<a href="/forum/Login">会員登録へ戻る</a> <a href="/forum">TOPへ戻る</a>
		<% } %>
			</div>
		<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>		
	</div>
</body>
</html>