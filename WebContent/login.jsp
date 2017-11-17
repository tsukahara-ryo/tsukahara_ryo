<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">



<form action="login" method="post"><br />
	<label for="login_id">loginid</label>
	<input name="login_id" id="login_id"/> <br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />

	<input type="submit" value="ログイン" /> <br />
	<a href="./">戻る</a>
</form>
</div>
</body>
</html>
