<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー編集</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="header">
		<div id="menu">
			<ul>
			<li><a href="./">ホーム</a></li>
			<li><a href="usermanagement">ユーザー管理</a></li>
			<li><a href="logout">ログアウト</a></li>
			</ul>
		</div>
	</div>
	<div class="settings">
		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${errorMessages}" var="message">
						<li><c:out value="${message}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="errorMessages" scope="session"/>
		</c:if>
		<form action="settings" method="post" enctype="multipart/form-data"><br />
			<label for="login_id">ログインID(半角英数字で6～20文字)</label>
			<input name="login_id" value="${editUser.login_id}" />
			<label for="name">名前(10文字以下)</label>
			<input name="name" value="${editUser.name}" id="name"/>
			<label for="password">パスワード(記号を含む半角英数字で6～20文字)</label>
			<input name="password" type="password" id="password"/>
			<label for="password2">確認パスワード</label>
			<input name="password2" type="password" id="password2"/> <br />
			<c:if test="${editUser.id != loginUser.id}">
				<label for="branch_id">店名</label>
				<select name="branch_id">
		    		<c:forEach var="branch" items="${branches}">
						<option value="${branch.id}" <c:if test="${branch.id == editUser.branch_id }"> selected </c:if>>
							<c:out value="${branch.name}"/>
						</option>
					</c:forEach>
				</select><br />
				<label for="position_id">部署・役職</label>
				<select name="position_id">
		   			<c:forEach var="position" items="${positions}">
						<option value="${position.id}"<c:if test="${position.id == editUser.position_id }"> selected </c:if>>
							<c:out value="${position.name}"/>
						</option>
					</c:forEach>
				 </select><br />
				<input type="hidden" name="id" value="${editUser.id}">
			</c:if>
			<input type="hidden" name="branch_id" value="${editUser.branch_id}">
			<input type="hidden" name="position_id" value="${editUser.position_id}">
			<input type="hidden" name="id" value="${editUser.id}"><br />
			<input type="submit" value="登録" /> <br />
		</form>
	</div>
</body>
</html>
