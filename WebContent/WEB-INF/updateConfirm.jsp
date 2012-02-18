<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<script type="text/javascript" src="script.js"></script>
<title>ユーザー管理システム：更新画面(確認)</title>
</head>
<body>
<div class="title">
ユーザー管理システム：更新画面(確認)
</div>
<div class="body">
<form action="updateConfirm" method="post">
<div class="form form-select" style="width: 40em;">
	<ul class="${validationMessage.type}">
		<c:forEach var="message" items="${validationMessage.messages}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	<p class="form-description">
	これでよろしいですか？
	</p>
	<table id="update-user-table">
		<tr>
			<td>
				<span class="form-label" style="display: inline;">ID:</span>
				<input type="text" name="userid" value="${param.userid}" class="input-text readonly" readonly="readonly" style="display: inline; " />
				<br /><br />
			</td>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td>
				<table id="update-user-table-before">
					<tr>
						<th style="text-align: left;">変更前</th>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th class="form-label">名前:</th>
						<td><input type="text" name="username_OLD" value="${param.username_OLD}" class="input-text readonly" readonly="readonly" /></td>
					</tr>
					<tr>
						<th class="form-label">TEL:</th>
						<td><input type="text" name="tel_OLD" value="${param.tel_OLD}" class="input-text readonly" readonly="readonly" /></td>
					</tr>
					<tr>
						<th class="form-label">パスワード:</th>
						<td><input type="password" name="pass_OLD" value="${param.pass_OLD}" class="input-text readonly" readonly="readonly" /></td>
					</tr>
				</table>
			</td>
			<td>⇒</td>
			<td>
				<table id="update-user-table-after">
					<tr>
						<th style="text-align: left;">変更後</th>
						<td>&nbsp;</td>
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
							<input type="password" name="passConfirm" class="input-text" maxlength="12" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr class="form-row">
			<td colspan="3">
				<input type="submit" value="更新" class="input-submit" />
				&nbsp;
				<button onclick="location.href='update'; return false;" class="input-submit">戻る</button>
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