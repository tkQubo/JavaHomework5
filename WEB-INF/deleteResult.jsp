<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8j">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<title>ユーザー管理システム：削除画面(完了)</title>
</head>
<body>
<div class="title">
ユーザー管理システム：削除画面(完了)
</div>
<div class="body">
${user.userName }さんが削除を完了しました。<br />
<form action="select" method="get">
	<input type="submit" value="検索画面" class="input-submit" />
</form>
<hr />
<a href="menu">メインメニューへ</a>
</div>
</body>
</html>