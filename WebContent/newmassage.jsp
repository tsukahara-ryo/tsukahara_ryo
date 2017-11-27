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
	<a href="./">ホーム</a>
	<a href="logout">ログアウト</a>
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
	<label>新規投稿</label>
		<form action="newmessage" method="post">
			<p><label for="subject">件名</label></p>
			<p><input name="subject"id="subject" value="${message.subject}" /> ※必須 30文字以下</p>
			<p><label for="cotegory">カテゴリー</label></p>
			<p><input name="cotegory" id="cotegory" value="${message.cotegory}" /> ※必須 10文字以下</p>
			<p><label for="text">本文</label></p>
			<p><textarea name="text" cols="50" rows="10" class="cotegry-box" ><c:out value="${message.text}" /></textarea> ※必須 1000文字以下</p>
			<p><input type="submit"value="投稿" /></p>
		</form>
		<input type="button" onclick="location.href='http://localhost:8080/tsukahara_ryo/newmessage'"value="クリア">
	</div>
</body>
</html>
