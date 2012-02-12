<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8j">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<script type="text/javascript" src="script.js"></script>
<title>ユーザー管理システム：検索</title>
</head>
<body onload="focusElement('userid')">
<div class="title">
ユーザー管理システム：検索
</div>
<div class="body">
	<form action="select" method="post">
	<div class="form" id="form-select">
		<ul class="${validationMessage.type}">
			<c:forEach var="message" items="${validationMessage.messages}">
				<li>${message}</li>
			</c:forEach>
		</ul>
		<p class="form-description">
			検索したいデータ情報を入力して下さい<br />
			※全て空白の場合は全検索を行います
		</p>
		<table id="update-table">
			<col class="select-column1" />
			<col class="select-column2" />
			<tr>
				<th class="form-label">ID:</th>
			</tr>
			<tr>
				<td><input type="text" name="userid" class="input-text" id="userid" value="${param.userid}" maxlength="9"/></td>
			</tr>
			<tr>
				<th class="form-label">名前:</th>
			</tr>
			<tr>
				<td>
					<input type="text" name="username" class="input-text" value="${param.username}" maxlength="12" />
				</td>
				<td>
					<select name="usernameMatchType" class="input-text-match">
						<option value="Contains"${(param.usernameMatchType eq 'Contains')?' selected':''}>部分一致</option>
						<option value="Exact"${(param.usernameMatchType eq 'Exact')?' selected':''}>完全一致</option>
						<option value="StartsWith"${(param.usernameMatchType eq 'StartsWith')?' selected':''}>前方一致</option>
						<option value="EndsWith"${(param.usernameMatchType eq 'EndsWith')?' selected':''}>後方一致</option>
					</select>
				</td>
			</tr>
			<tr>
				<th class="form-label">TEL:</th>
			</tr>
			<tr>
				<td>
					<input type="text" name="tel" class="input-text" value="${param.tel}" maxlength="12" />
				</td>
				<td>
					<select name="telMatchType" class="input-text-match">
						<option value="Contains"${(param.telMatchType eq 'Contains')?' selected':''}>部分一致</option>
						<option value="Exact"${(param.telMatchType eq 'Exact')?' selected':''}>完全一致</option>
						<option value="StartsWith"${(param.telMatchType eq 'StartsWith')?' selected':''}>前方一致</option>
						<option value="EndsWith"${(param.telMatchType eq 'EndsWith')?' selected':''}>後方一致</option>
					</select>
				</td>
			</tr>
			<tr class="form-row">
				<td colspan="2"><input type="submit" value="検索" class="input-submit" /></td>
			</tr>
		</table>
	</div>
	</form>
	<hr />
	<a href="menu">メインメニューへ</a>
</div>
</body>
</html>