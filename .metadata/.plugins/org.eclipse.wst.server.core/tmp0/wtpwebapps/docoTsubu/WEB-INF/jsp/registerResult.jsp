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
<c:choose>
	<c:when test="${flag }">
		新規登録完了
	</c:when>
	<c:otherwise>
		新規登録に失敗しました
	</c:otherwise>
</c:choose>
<p>
<a href="/docoTsubu/Main">トップへ</a>
</p>
</body>
</html>