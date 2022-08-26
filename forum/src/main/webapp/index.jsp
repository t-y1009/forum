<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/forum/css/index.css">
<meta charset="UTF-8">
<title>共有掲示板</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="login-box">
				<div class="login-title">共有掲示板</div>
				<div class="login-form">
					<form action="/forum/Login" method="post">
						<div class="form-group1">
							<label class="form-control-label">ユーザーID</label><br>
							<input type="text" name="user_id" class="form-control">
						</div>
						<div class="form-group2">
							<label class="form-control-label">パスワード</label><br>
							<input type="password" name="password" class="form-control">
						</div>
						<div class="loginbttm">
							<div class="login-text"></div>
							<div class="login-button">
								<button type="submit" class="btn-outline-primary">ログイン</button>
								<a href="/forum/Login" class="register">新規登録</a>
							</div>
						</div>
					</form>
	    		</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>