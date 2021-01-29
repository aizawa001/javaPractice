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
<h1>つぶやき詳細</h1>
<p>
<c:out value="${mutter.userName }"/>:
<c:out value="${mutter.text }"/>
</p>
<p><a href="/docoTsubu/Main">戻る</a></p>
</body>
</html>