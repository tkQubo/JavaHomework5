<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8j">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<script type="text/javascript" src="script.js"></script>
<title>ユーザー管理システム：登録画面</title>
</head>
<body onload="focusElement('username')">
<div class="title">
ユーザー管理システム：登録画面
</div>
<div class="body">
<form action="insert" method="post">
<div class="form form-select">
	<ul class="${validationMessage.type}">
		<c:forEach var="message" items="${validationMessage.messages}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	<p class="form-description">
	必須項目を入力して下さい<br />
	<span class="required">※</span>は必須です
	</p>
	<table id="edit-user-table">
		<tr>
			<th class="form-label">ID:</th>
			<td><input type="text" value="自動で設定されます" class="input-text auto" readonly="readonly" disabled="disabled" /></td>
		</tr>
		<tr>
			<th class="form-label">名前:</th>
			<td><input type="text" name="username" value="${param.username}" class="input-text" id="username" maxlength="12" /></td>
		</tr>
		<tr>
			<th class="form-label">TEL:</th>
			<td><input type="text" name="tel" value="${param.tel}" class="input-text" maxlength="12" /></td>
		</tr>
		<tr>
			<th class="form-label"><span class="required">※</span>パスワード:</th>
			<td><input type="password" name="pass" class="input-text" maxlength="12" /></td>
		</tr>
		<tr class="form-row">
			<td colspan="2">
				<input type="submit" value="確認" class="input-submit" />
			</td>
		</tr>
	</table>
</div>
<hr />
<a href="menu">メインメニューへ</a>
</form>
</div>
</body>
</html>