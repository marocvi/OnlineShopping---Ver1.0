<%@ page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style type="text/css">
.error {
	color: red;
	text-decoration: inherit;
}
</style>
</head>
<body>
	<div class="container">
		<div class="account">
			<h2 class="account-in">Enter your Account</h2>
			<form action="login" method="post">
				<p class = "error" ><c:out value="${error.auth}"></c:out></p>
				<div>
					<span class="mail">User Name*</span><br> <input type="text"
						name="email" value = "${user.email}">
					<p class="error">
						<c:out value="${error.emailNull}"></c:out>
					<p>
				</div>
				<div>
					<span class="word">Password*</span><br> <input type="password"
						name="password">
					<p class="error">
						<c:out value="${error.passwords}"></c:out>
					<p>
				</div>
				<input type="submit" value="Login">
			</form>
		</div>
	</div>
</body>
</html>


