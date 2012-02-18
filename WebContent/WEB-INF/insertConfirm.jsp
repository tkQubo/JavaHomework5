<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8j">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<script type="text/javascript" src="script.js"></script>
<title>ユーザー管理システム：登録画面(確認)</title>
</head>
<body onload="focusElement('passConfirm');">
<div class="title">
ユーザー管理システム：登録画面(確認)
</div>
<div class="body">
<form action="insertConfirm" method="post">
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
			<td><input type="text" value="自動で設定されます" class="input-text auto" readonly="readonly" disabled="disabled" /></td>
		</tr>
		<tr>
			<th class="form-label">名前:</th>
			<td><input type="text" name="username" value="${param.username}" class="input-text readonly" readonly="readonly" /></td>
		</tr>
		<tr>
			<th class="form-label">TEL:</th>
			<td><input type="text" name="tel" value="${param.tel}" class="input-text readonly" readonly="readonly" /></td>
		</tr>
		<tr>
			<th class="form-label">パスワード(再入力):</th>
			<td>
				<input type="hidden" name="pass" value="${param.pass}" />
				<input type="password" name="passConfirm" class="input-text" id="passConfirm" maxlength="12" />
			</td>
		</tr>
		<tr class="form-row">
			<td colspan="2">
				<input type="submit" value="登録" class="input-submit" />
				&nbsp;
				<button onclick="history.back();" class="input-submit">戻る</button>
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