<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="stylesheet.css" type="text/css">
<script type="text/javascript" src="script.js"></script>
<title>ユーザー管理システム：ログイン</title>
</head>
<body onload="focusElement('userid');">
<div class="title">
ユーザー管理システム：ログイン
</div>
<form action="login" method="post">
<c:if test="${not empty(redirect)}">
	<input type="hidden" name="redirect" value="${redirect}" />
</c:if>
<div class="login-form form">
	<div class="login-form-contents">
		<ul class="${validationMessage.type}">
			<c:forEach var="message" items="${validationMessage.messages}">
				<li>${message}</li>
			</c:forEach>
		</ul>
		<div class="form-description">
			IDとPASSを入力して下さい。
		</div>
		<span class="form-label">ID:</span>
		<input type="text" name="userid" class="input-text" value="${param.userid}" id="userid" maxlength="9" />
		<span class="form-label">PASS:</span>
		<input type="password" name="pass" class="input-text" maxlength="12" />
		<br />
		<div class="form-row">
			<input type="submit" value="login" class="input-submit" />
		</div>
	</div>
</div>
</form>
</body>
</html>