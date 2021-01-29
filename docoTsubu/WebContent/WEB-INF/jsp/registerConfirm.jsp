<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>新規登録確認画面</h1>
<form action="/docoTsubu/RegisterUser?para=1" method="post">
ID：<input type="text" name="id" value=${registerUser.id } readonly><br>
名前：<input type="text" name="name" value=${registerUser.name } readonly><br>
<input type="hidden" name="pass" value=${registerUser.pass }>
<input type="submit" value="登録">
</form>
<p><a href="/docoTsubu/RegisterUser">戻る</a></p>
</body>
</html>