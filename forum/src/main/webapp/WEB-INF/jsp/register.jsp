<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/forum/css/index.css">
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="login-box">
			<div class="login-title">会員登録</div>
			<div class="login-form">
				<form action="/forum/Login" method="post">
					<div class="form-group1">
						<label class="form-control-label">ユーザーID</label><br>
						<input type="text" name="user_id" required pattern="^[a-zA-Z0-9]+$" maxlength="10" class="form-control">
					</div>
					<div class="form-group1">
						<label class="form-control-label">パスワード</label><br>
						<input type="password" name="password" required class="form-control">
					</div>
					<div class="form-group1">
						<label class="form-control-label">名前</label><br>
						<input type="text" name="name" required class="form-control">
					</div>
						<input type="hidden" name="account_type" value="0">
					<div class="loginbttm">
						<div class="login-text"></div>
						<div class="login-button">
							<button type="submit" class="btn-outline-primary">登録</button>
							<a href="/forum/"class="btn_top">TOPへ</a>
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