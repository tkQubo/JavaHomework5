<%@page import="com.qubo.caea05.Configuration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8j">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<title>ユーザー管理システム：検索結果</title>
</head>
<body>
<div class="title">
ユーザー管理システム：検索結果
</div>
<div class="body">
	<p>
		検索条件:
		<c:if test="${empty(param.userid) and empty(param.username) and empty(param.tel)}">
			<i>全検索</i>
		</c:if>
		<c:if test="${not(empty(param.userid) and empty(param.username) and empty(param.tel))}">
			<table id="query-table">
				<c:if test="${not(empty(param.userid))}">
					<tr>
						<th>ID:</th>
						<td>${param.userid}</td>
					</tr>
				</c:if>
				<c:if test="${not(empty(param.username))}">
					<tr>
						<th>名前:</th>
						<td>
							<c:out value="${param.username}" />
							<c:choose>
								<c:when test="${param.usernameMatchType eq 'Contains'}">(部分一致)</c:when>
								<c:when test="${param.usernameMatchType eq 'Exact'}">(完全一致)</c:when>
								<c:when test="${param.usernameMatchType eq 'StartsWith'}">(前方一致)</c:when>
								<c:when test="${param.usernameMatchType eq 'EndsWith'}">(後方一致)</c:when>
							</c:choose>
						</td>
					</tr>
				</c:if>
				<c:if test="${not(empty(param.tel))}">
					<tr>
						<th>TEL:</th>
						<td>
							<c:out value="${param.tel}" />
							<c:choose>
								<c:when test="${param.telMatchType eq 'Contains'}">(部分一致)</c:when>
								<c:when test="${param.telMatchType eq 'Exact'}">(完全一致)</c:when>
								<c:when test="${param.telMatchType eq 'StartsWith'}">(前方一致)</c:when>
								<c:when test="${param.telMatchType eq 'EndsWith'}">(後方一致)</c:when>
							</c:choose>
						</td>
					</tr>
				</c:if>
			</table>
		</c:if>
	</p>
	<table border="1" class="select-result">
		<col class="column-userid" />
		<col class="column-username" />
		<col class="column-tel" />
		<% if (Configuration.showPassword) { %>
			<col class="column-pass" />
		<% } %>
		<tr class="form-label">
			<th>ID</th>
			<th>名前</th>
			<th>TEL</th>
			<% if (Configuration.showPassword) { %>
				<th>PASS</th>
			<% } %>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.userId}&nbsp;</td>
				<td><c:out value="${user.userName}" />&nbsp;</td>
				<td><c:out value="${user.tel}" />&nbsp;</td>
				<% if (Configuration.showPassword) { %>
					<td><c:out value="${user.pass}" /></td>
				<% } %>
			</tr>
		</c:forEach>
	</table>
	<hr />
	<form action="multi" method="post">
	<div class="form form-dispatch">
		<input type="hidden" name="userid" id="userid" />
		<p class="form-description">
			<b>実行したい処理を選んでください</b><br />
			IDを入力し処理画面へ移動します
		</p>
		<table>
			<tr>
				<td><input type="text" value="IDは自動入力です" class="input-text auto" disabled="disabled" readonly="readonly" /></td>
				<td><input type="submit" name="insert" value="登録画面" class="input-submit" /></td>
			</tr>
			<tr>
				<td><input type="text" name="updateId" id="updateId" class="input-text" maxlength="9" /></td>
				<td><input type="submit" name="update" value="更新画面" class="input-submit" onclick="onSubmit('updateId')" /></td>
			</tr>
			<tr>
				<td><input type="text" name="deleteId" id="deleteId" class="input-text" maxlength="9" /></td>
				<td><input type="submit" name="delete" value="削除画面" class="input-submit" onclick="onSubmit('deleteId')" /></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
	function onSubmit(target) {
		var userid = document.getElementById("userid");
		var from = document.getElementById(target);
		userid.value = from.value;
	}
	</script>
	</form>
	<hr />
	<form action="multi" method="post">
	<div class="form form-dispatch">
		<p class="form-description">
			IDを入力し処理画面へ移動します<br />
			※「登録」では入力したIDは無視されます
		</p>
		<table>
			<tr>
				<td><input type="text" name="userid" class="input-text" maxlength="9" /></td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="insert" value="登録" class="input-submit" />
					<input type="submit" name="update" value="更新" class="input-submit" />
					<input type="submit" name="delete" value="削除" class="input-submit" />
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