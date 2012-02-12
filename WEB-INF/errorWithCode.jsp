<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../stylesheet.css" type="text/css">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<title>ユーザー管理システム：エラー</title>
</head>
<body>
<div class="title">
ユーザー管理システム：リクエストエラー
</div>
<div class="body">
<h2>HTTPステータス ${code}</h2>
<p class="error">
	${message}
</p>
<hr />
<a href="menu">メインメニューへ</a>
</div>
</body>
</html>