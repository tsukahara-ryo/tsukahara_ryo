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

	<a href="./">ホーム</a>
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



	<form action="newmessage" method="post">
		<p><label for="subject">件名</label></p>
		<p><input name="subject"id="subject" /> ※必須 ３０文字以下</p>
		<p><label for="cotegory">カテゴリー</label></p>
		<p><input name="cotegory" id="cotegory" /> ※必須 １０文字以下</p>
		<p><label for="text">本文</label></p>
		<p><textarea name="text" cols="50" rows="10" class="cotegry-box" ></textarea> ※必須 ５００文字以下</p>
		<p><input type="submit"value="投稿" /></p>
		<a href="./">戻る</a>
	</form>



	<div class="copyright">Copyright(c)Ryo Tsukahara</div>
</body>
</html>
