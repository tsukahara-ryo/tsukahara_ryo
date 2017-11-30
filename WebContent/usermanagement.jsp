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
	<div class="header">
		<div id="menu">
			<ul>
			<li><a href="./">ホーム</a></li>
			<li><a href="signup">新規登録</a></li>
			<li><a href="logout">ログアウト</a></li>
			</ul>
		</div>
	</div>
	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="comment">
					<li><c:out value="${comment}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session" />
	</c:if>
	<div class="alluser">
		<table border="1" cellspacing="0" bordercolor="#000000">
			<caption>ユーザー情報一覧</caption>
			<tr>
				<th width="200" height="40">名前</th>
				<th width="200" >支店名</th>
				<th width="200" >部署・役職</th>
				<th width="100" >編集</th>
				<th width="100" >停止・復活</th>
			</tr>
			<c:forEach var="user" items="${users}">
				<tr>
					<td height="40"><c:out value="${user.name}" /></td>
					<td height="40">
						<c:forEach var="branch" items="${branches}">
							<c:if test="${user.branch_id == branch.id}">
								<c:out value="${branch.name}" />
							</c:if>
						</c:forEach>
					</td>
					<td height="40">
						<c:forEach var="position" items="${positions}">
							<c:if test="${user.position_id == position.id}">
								<c:out value="${position.name}" />
							</c:if>
						</c:forEach>
					</td>
					<td align="center" valign="middle" height="40">
						<div class="settingbutton">
							<form action="settings" method="get">
								<input type="hidden" name="id" value="${user.id}" />
								<input type="submit" value="編集" style="margin: 0px; float: left;" />
							</form>
						</div>
					</td>
					<td align="center" valign="middle" height="40">
						<c:if test="${user.id != loginUser.id}">
							<div class="isdeletedbutton">
								<form action="isDeleted" method="post">
									<input type="hidden" name="id" value="${user.id}" />
									<c:if test="${user.is_deleted == 0}">
										<input type="hidden" name="isdeleted" value="1" />
										<input id="stopbutton" type="submit" value="停止" onClick="return confirm('停止しますか？')" style="margin: 0px; float: left;" />
									</c:if>
									<c:if test="${user.is_deleted == 1}">
										<input type="hidden" name="isdeleted" value="0" />
										<input id="bornbutton" type="submit" value="復活" onClick="return confirm('復活しますか？')" style="margin: 0px; float: left;" />
									</c:if>
								</form>
							</div>
						</c:if>
						<c:if test="${user.id == loginUser.id}">
							ログイン中
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
