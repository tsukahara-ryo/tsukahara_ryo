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

<a href="./">ホーム</a>
<a href="usermanagement">ユーザー編集</a>
<div class="main-contents">

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
	<label for="name">名前</label>
	<input name="name" value="${editUser.name}" id="name"/><br />

	<label for="login_id">ログインID</label>
	<input name="login_id" value="${editUser.login_id}" /><br />
	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />
	<label for="password2">確認パスワード</label>
	<input name="password2" type="password" id="password2"/> <br />

	<label for="branch_id">店名</label>
		<select name="branch_id">
    		<c:forEach var="branch" items="${branches}">
				<option value="${branch.id}"><c:out value="${branch.name}"/> </option>
			</c:forEach>
		</select><br />

	<label for="position_id">部署・役職</label>
		<select name="position_id">
   			 <c:forEach var="position" items="${positions}">
				<option value="${position.id}"><c:out value="${position.name}"/> </option>
			</c:forEach> </select><br />
	<input type="hidden" name="id" value="${editUser.id}">
	<input type="submit" value="登録" /> <br />
	<a href="./">戻る</a>
</form>

</div>
</body>
</html>
