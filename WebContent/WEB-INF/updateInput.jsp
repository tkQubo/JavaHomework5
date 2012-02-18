<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<script type="text/javascript" src="script.js"></script>
<title>ユーザー管理システム：更新画面(変更)</title>
</head>
<body onload="focusElement('username')">
<div class="title">
ユーザー管理システム：更新画面(変更)
</div>
<div class="body">
<form action="updateInput" method="post">
<input type="hidden" name="userid_OLD" value="${requestScope.user.userId}" />
<input type="hidden" name="username_OLD" value="${requestScope.user.userName}" />
<input type="hidden" name="tel_OLD" value="${requestScope.user.tel}" />
<input type="hidden" name="pass_OLD" value="${requestScope.user.pass}" />
<div class="form form-select">
	<ul class="${validationMessage.type}">
		<c:forEach var="message" items="${validationMessage.messages}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	<p class="form-description">
	必須項目を入力して下さい<br />
	<span class="required">※</span>IDは変更できません
	</p>
	<table id="edit-user-table">
		<tr>
			<th class="form-label">ID:</th>
			<td><input type="text" name="userid" value="${empty(param.userid) ? requestScope.user.userId : param.userid}" class="input-text readonly" readonly="readonly" /></td>
		</tr>
		<tr>
			<th class="form-label">名前:</th>
			<td><input type="text" name="username" value="${empty(param.username) ? requestScope.user.userName : param.username}" class="input-text" maxlength="12" id="username" /></td>
		</tr>
		<tr>
			<th class="form-label">TEL:</th>
			<td><input type="text" name="tel" value="${empty(param.tel) ? requestScope.user.tel : param.tel}" class="input-text" maxlength="12" /></td>
		</tr>
		<tr>
			<th class="form-label">パスワード:</th>
			<td><input type="password" name="pass" value="${empty(param.pass) ? requestScope.user.pass : param.pass}" class="input-text" maxlength="12" /></td>
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