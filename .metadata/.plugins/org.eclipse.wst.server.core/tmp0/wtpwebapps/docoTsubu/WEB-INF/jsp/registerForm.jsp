<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶ新規登録</h1>
<form action="/docoTsubu/RegisterUser?para=0" method="post">
ID：<input type="text" name="id"><br>
名前：<input type="text" name="name"><br>
パスワード：<input type="password" name="pass"><br>
<input type="submit" value="確認">
</form>
<p><a href="/docoTsubu/Main">戻る</a></p>
</body>
</html>