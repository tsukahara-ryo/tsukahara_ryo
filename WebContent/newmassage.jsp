<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="header">
		<div id="menu">
			<ul>
			<li><a href="./">ホーム</a></li>
			<li><a href="logout">ログアウト</a></li>
			</ul>
		</div>
	</div>
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<li><c:out value="${message}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session" />
	</c:if>
	<div class="newmessage">
		<h4>新規投稿</h4>
		<form action="newmessage" method="post">
			<label for="subject">件名(30文字以下)</label>
			<input name="subject"id="subject" value="${message.subject}" />
			<label for="cotegory">カテゴリー(10文字以下)</label>
			<input name="cotegory" id="cotegory" value="${message.cotegory}" />
			<label for="text">本文(1000文字以下)</label>
			<textarea name="text" cols="90" rows="10" class="message-box"  ><c:out value="${message.text}" /></textarea>
			<br><input type="submit"value="投稿" />
		</form>
	</div>
</body>
</html>
