<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div class="main-contents">
		<div class="header">
			<c:if test="${ empty loginUser }">
				<a href="login">ログイン</a>
			</c:if>
			<c:if test="${ not empty loginUser }">
				<div id="menu">
				<ul>
				<li><a href="newmessage">新規投稿</a></li>
				<c:if test="${loginUser.position_id == 1 }">
				<li><a href="usermanagement">ユーザー管理</a></li>
				</c:if>
				<li><a href="logout">ログアウト</a></li>
				</ul>
				</div>
			</c:if>
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
		<c:if test="${ not empty loginUser }">
			<div class="search">
				<span class="box-title">絞込み</span>
				<form action="" method="get">
					カテゴリー<p><input name="cotegory" id="cotegory" value="${cotegory}" style="width:133px;"/></p>
					<p>投稿日時</p>
					<input type="date" id="begin" name="begin" value="${begin}" />～
					<input type="date" id="end" name="end" value="${end}" />
					<input type="submit"	value="絞込み" />
					<input type="button" onclick="location.href='http://localhost:8080/tsukahara_ryo/'"value="クリア">
				</form>
			</div>
			<c:forEach items="${messages}" var="message">
				<div class="message">
					件名
					<div class="subject">
						 <br><c:out value="${message.subject}" />
					</div>
					<br>カテゴリー
					<div class="cotegory">
						 <br><c:out value="${message.cotegory}" />
					</div>
					<br>本文
					<div class="text">
						 <c:forEach var="str" items="${fn:split(message.text,'
')}" ><c:out value="${str}" /><br></c:forEach>
					</div>
					<div class="name">投稿者：<c:out value="${message.name}" /></div>
					投稿時間
					<div class="messagedate">
						<fmt:formatDate value="${message.insertDate}"
							pattern="yyyy/MM/dd HH:mm:ss" />
					</div>
					<input type="hidden" name="id" value="${message.messagesid}">
					<c:if test="${message.userId == loginUser.id }">
						<form action="del" method="post">
							<br /> <input type="hidden" name="id" value="${message.messagesid}">
							<input type="submit" value="投稿削除"onClick="return confirm('削除しますか？')" /> <br />
						</form>
					</c:if>
				</div>
				<c:forEach items="${comments}" var="comment">
					<c:if test="${message.messagesid == comment.message_id }">
						<div class="comment">
						コメント<br />
							<div class="text">
							<c:forEach var="str" items="${fn:split(comment.text,'
')}" ><c:out value="${str}" /><br>
							</c:forEach>
							</div>
							<div class="date">
								<c:out value="${comment.name}" /><br>
								<fmt:formatDate value="${comment.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" />
							</div>
							<c:if test="${comment.user_id == loginUser.id }">
								<form action="comdel" method="post">
								<br /> <input type="hidden" name="id" value="${comment.id}">
								<input type="submit" value="コメント削除"onClick="return confirm('削除しますか？')" /> <br />
								</form>
							</c:if>
						</div>
					</c:if>
				</c:forEach>
				<div class="form-area">
					<form action="newComment" method="post">
						コメント<br />
						<textarea name="comment" cols="100" rows="5" class="comment-box"></textarea>
						<input type="hidden" name="message_id" value="${message.messagesid}"> <br />
						<input type="submit" value="コメント">（500文字以下）
					</form>
				</div>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>
