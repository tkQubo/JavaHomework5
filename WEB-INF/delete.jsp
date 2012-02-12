<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8j">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<script type="text/javascript" src="script.js"></script>
<title>ユーザー管理システム：削除画面</title>
</head>
<body onload="focusElement('userid')">
<div class="title">
ユーザー管理システム：削除画面
</div>
<div class="body">
	<form action="delete" method="post">
	<div class="form form-select">
		<ul class="${validationMessage.type}">
			<c:forEach var="message" items="${validationMessage.messages}">
				<li>${message}</li>
			</c:forEach>
		</ul>
		<p class="form-description">
			削除を行うデータのIDを入力して下さい<br />
		</p>
		<table id="update-table" width="100%">
			<col class="select-column1" />
			<col class="select-column2" />
			<tr>
				<th class="form-label">ID:</th>
			</tr>
			<tr>
				<td><input type="text" name="userid" class="input-text" id="userid" value="${param.userid}" maxlength="9" /></td>
			</tr>
			<tr class="form-row">
				<td colspan="2"><input type="submit" value="確認" class="input-submit" /></td>
			</tr>
		</table>
	</div>
	</form>
	<hr />
	<a href="menu">メインメニューへ</a>
</div>
</body>
</html>