<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<title>ユーザー管理システム：メニュー</title>
</head>
<body>
<div class="title">
ユーザー管理システム：メインメニュー
</div>
<div class="body">
	<p>
		${user.userName}さん、こんにちは。
	</p>
	<ul id="menu">
		<li><a href="select">検索</a></li>
		<li><a href="insert">登録</a></li>
		<li><a href="update">更新</a></li>
		<li><a href="delete">削除</a></li>
		<li><a href="lump">一括</a></li>
		<li><a href="logout">ログアウト</a></li>
	</ul>
</div>
</body>
</html>