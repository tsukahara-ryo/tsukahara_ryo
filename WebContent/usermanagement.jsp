<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="./">ホーム</a>
<a href="signup">新規登録</a>
	<label for="users">ユーザー一覧</label>
	<br><c:forEach var="user" items="${users}">
	<div class="management">
	<c:out value="${user.name}"/>

		<c:forEach var="branch" items="${branches}">
			<c:if test="${user.branch_id == branch.id}">
				<c:out value="${branch.name}"/>
			</c:if>
		</c:forEach>

		<c:forEach var="position" items="${positions}">
			<c:if test="${user.position_id == position.id}">
				<c:out value="${position.name}" />
			</c:if>
		</c:forEach>

	<form action="settings" method="get">
	<input type="hidden" name="id" value="${user.id}">
	<input type="submit" value="編集"  style="margin:0px; float:left;"/>
	</form>

	<c:if test="${user.is_deleted == 0}">
		<form action="isDeleted" method="Post">
		<input type="hidden" name="isdeleted" value="1">
		<input type="hidden" name="id" value="${user.id}">
		<input id = "stopbutton" type="submit" value="停止" onClick="return confirm('停止しますか？')"  style="margin:0px; float:left;"/>
		</form>

	</c:if>
	<c:if test="${user.is_deleted == 1}">
		<form action="isDeleted" method="Post">
		<input type="hidden" name="isdeleted" value="0">
		<input type="hidden" name="id" value="${user.id}">
		<input id = "bornbutton" type="submit" value="復活"onClick="return confirm('復活しますか？')"  style="margin:0px; float:left;"/>
		</form>
	</c:if>
	<br/>
	</div>
	</c:forEach><br />

	<a href="./">戻る</a>
</body>
</html>
