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
<form action="/docoTsubu/RegisterUser" method="post" action="done">
名前：<input type="text" name="name" value=${registerUser.name }><br>
パスワード：<input type="password" name="pass" value=${registerUser.pass }><br>
<input type="submit" value="確認">
</form>
</body>
</html>