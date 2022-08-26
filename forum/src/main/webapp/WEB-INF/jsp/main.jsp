<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Mutter, model.GetMutterListLogic, java.util.List " %>
<% User loginUser = (User)session.getAttribute("loginUser"); 
   List<Mutter> mutterList = (List<Mutter>)request.getAttribute("mutterList");
   String errorMsg = (String)request.getAttribute("errorMsg"); 
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/forum/css/forum.css">
<meta charset="UTF-8">
<title>共有掲示板</title>
</head>
<body>
	<header>
		<div class="header_title">
			<div class="serch">
				<form action="/forum/Main" method="post">
					<input type="text" name="words" placeholder="検索フォーム">
					<input type="hidden" name="flag" value="4">
					<input type="submit" value="検索" id="form_serch_btn">
				</form>
			</div>
		</div>
		<ul class="gnav">
		    <li>
		    	<div class="menu">
			    	<img src="/forum/img/menu.png" class="menu_icon">
			        <span>ようこそ <%= loginUser.getName() %>さん</span>
		        </div>
		        <ul>
		        	<li><a href="/forum/Main?favorite=find">お気に入り一覧</a></li>
		            <li><a href="/forum/Logout">ログアウト</a></li>
		        </ul>
		    </li>
		</ul>
	</header>
	<div id="wrapper">
		<div class="container">
			<div class="row">
				<div class="form_group">
					<form action="/forum/Main" method="post">
						<input type="text" class="text_box" name="text" placeholder="メッセージを入力してください">
						<input type="hidden" name="flag" value="1">
						<input type="submit" value="つぶやく">
					</form>	
				</div>
				<div class="errorMsg">
					<% if(errorMsg != null){ %>
					<p><%= errorMsg %></p>
					<% } %>
				</div>
				<div class="main">
					<form action="/forum/Main" method="post" id="form_reload">
						<input type="hidden" name="reload" value="1">
						<input type="hidden" name="flag" value="2">
						<input type="submit" value="更新">
					</form>
					<div class="forum_list">
						<% for(Mutter mutter : mutterList){ %>
							<% if(loginUser.getAccountType() == 1 ){ %>
							<form action="/forum/Main" method="post">
								<input type="hidden" name="id" value="<%= mutter.getId()%>">
								<input type="hidden" name="flag" value="3">
								<input type="submit"  value="削除">
							</form>
							<% } %>
							<div class="list">
								<div class="contents">
									<p class="forum_user_name">ユーザー名:<span><%= mutter.getUserName() %></span></p>
									<p class="forum_text"> <%= mutter.getText() %> </p>
								</div>
								<div class="others">
									<div class="date"><p><%= mutter.getDate() %></p></div>
									<div class="favorite">
										<form name="favoriteFlag" action="/forum/Main" method="post">
											<input type="hidden" name="flag" value="5">
											<input type="hidden" name="id" value="<%= mutter.getId()%>">
											<input type="image" name="favorite_action" style="width:30px; height:34px;"src=<% if(mutter.getFavorite().equals("0")){ %>
												"/forum/img/non_favorite.png"
												<%}else{ %>
													"/forum/img/favorite.png"
												<% } %>>
										</form>
										<span class="favorite_count"><%= mutter.getFavorite() %></span>
									</div>
								</div>	
							</div>
						<% } %>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
	</div>
</body>
</html>