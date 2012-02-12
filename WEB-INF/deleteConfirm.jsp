<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8j">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<title>ユーザー管理システム：削除画面(確認)</title>
</head>
<body>
<div class="title">
ユーザー管理システム：削除画面(確認)
</div>
<div class="body">
<form action="deleteConfirm" method="post">
<div class="form form-select">
	<ul class="${validationMessage.type}">
		<c:forEach var="message" items="${validationMessage.messages}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	<p class="form-description">
	これでよろしいですか？
	</p>
	<table id="edit-user-table">
		<tr>
			<th class="form-label">ID:</th>
			<td><input type="text" name="userid" value="${requestScope.user.userId}" class="input-text readonly" readonly="readonly" /></td>
		</tr>
		<tr>
			<th class="form-label">名前:</th>
			<td><input type="text" name="username" value="${requestScope.user.userName}" class="input-text readonly" readonly="readonly" /></td>
		</tr>
		<tr>
			<th class="form-label">TEL:</th>
			<td><input type="text" name="tel" value="${requestScope.user.tel}" class="input-text readonly" readonly="readonly" /></td>
		</tr>
		<tr class="form-row">
			<td colspan="2">
				<input type="submit" value="削除" class="input-submit" />
				&nbsp;
				<button onclick="location.href='delete'; return false;" class="input-submit">戻る</button>
			</td>
		</tr>
	</table>
</div>

</form>
<hr />
<a href="menu">メインメニューへ</a>
</div>
</body>
</html>