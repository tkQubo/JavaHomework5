<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8j">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<link rel="stylesheet" href="../stylesheet.css" type="text/css">
<script type="text/javascript" src="script.js"></script>
<title>ユーザー管理システム：一括更新画面</title>
</head>
<body>
<div class="title">
ユーザー管理システム：一括更新画面
</div>
<div class="body">
	<form action="lump" method="post">
	<div class="form form-lump">
		<ul class="${validationMessage.type}">
			<c:forEach var="message" items="${validationMessage.messages}">
				<li>${message}</li>
			</c:forEach>
		</ul>
		<p class="form-description">
			実行者：<c:out value="${user.userName}" /><br />
			一括更新を行います<br />
			必要項目を入力して下さい<br />
		</p>
		<table id="" border="1">
			<col class="column-userid" />
			<col class="column-username" />
			<col class="column-tel" />
			<col class="column-pass" />
			<tr>
				<th>ID</th>
				<th>名前</th>
				<th>TEL</th>
				<th>PASS</th>
				<th>削除</th>
			</tr>
			<c:forEach var="user" items="${users}">
				<tr>
					<td><input type="text" value="${user.userId}" class="input-text readonly" readonly="readonly" disabled="disabled" /></td>
					<td><input type="text" value="${user.userName}" class="input-text" name="username${user.userId}" maxlength="12" /></td>
					<td><input type="text" value="${user.tel}" class="input-text" name="tel${user.userId}" maxlength="12" /></td>
					<td><input type="text" value="${user.pass}" class="input-text" name="pass${user.userId}" maxlength="12" /></td>
					<td><input type="checkbox" name="delete${user.userId}" /></td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<table border="1">
			<caption align="left"><b>新規登録</b></caption>
			<col class="column-userid" />
			<col class="column-username" />
			<col class="column-tel" />
			<col class="column-pass" />
			<tr>
				<th>ID</th>
				<th>名前</th>
				<th>TEL</th>
				<th>PASS</th>
			</tr>
			<tr>
				<td><input type="text" value="自動入力されます" class="input-text readonly" readonly="readonly" disabled="disabled" /></td>
				<td><input type="text" value="${userName}" class="input-text" name="username" maxlength="12" /></td>
				<td><input type="text" value="${tel}" class="input-text" name="tel" maxlength="12" /></td>
				<td><input type="text" value="${pass}" class="input-text" name="pass" maxlength="12" /></td>
			</tr>
		</table>
		<br />
		<div style="width: 50em; text-align: right;">
			<input type="submit" value="実行" class="input-submit" />
		</div>
		<br />
		<c:if test="${deletes gt 0 or updates gt 0 or inserts gt 0}">
			<table border="1">
				<caption align="left" style="color: blue;"><b>実行結果</b></caption>
				<tr>
					<th>削除件数</th>
					<th>更新件数</th>
					<th>登録件数</th>
				</tr>
				<tr>
					<c:if test="${deletes gt 0}">
						<td align="right" class="changed-count">${deletes}</td>
					</c:if>
					<c:if test="${deletes eq 0}">
						<td align="right">${deletes}</td>
					</c:if>
					<c:if test="${updates gt 0}">
						<td align="right" class="changed-count">${updates}</td>
					</c:if>
					<c:if test="${updates eq 0}">
						<td align="right">${updates}</td>
					</c:if>
					<c:if test="${inserts gt 0}">
						<td align="right" class="changed-count">${inserts}</td>
					</c:if>
					<c:if test="${inserts eq 0}">
						<td align="right">${inserts}</td>
					</c:if>
				</tr>
			</table>
		</c:if>
	</div>
	</form>
	<form action="logout" method="post">
	<div style="width: 52em; text-align: right;">
		<input type="submit" value="ログアウト" class="input-submit" />
	</div>
	</form>
	<hr />
	<a href="menu">メインメニューへ</a>
</div>
</body>
</html>