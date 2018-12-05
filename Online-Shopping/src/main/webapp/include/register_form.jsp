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
			<h2 class="account-in">Sign Up New Account</h2>
			<form action="register" method="post">
				<div>
					<span>First Name*</span><br> <input type="text"
						name="firstName" value = "${user.firstName}">
					<p class="error">
						<c:out value="${error.firstName}"></c:out>
					<p>
				</div>
				<div>
					<span class="name-in">Last Name*</span><br> <input type="text"
						name="lastName" value="${user.lastName}">
					<p class="error">
						<c:out value="${error.lastName}"></c:out>
					<p>
				</div>
				<div>
					<span class="phone">Phone Number</span><br> <input type="text"
						name="phone"  value= "${user.phone}"/>
					<p class="error">
						<c:out value="${error.phone}"></c:out>
					<p>
				</div>
				<div>
					<span class="address" >Address</span><br> <input type="text"
						name="address" value = "${user.address}">
					<p class="error">
						<c:out value="${error.address}"></c:out>
					<p>
				</div>
				<%--Checking where user exist in DB  using ajax --%>
				<div>
					<span class="mail">User Name*</span><br> <input type="text"
						name="email" value = "${user.email}">
					<p class="error">
						<c:out value="${error.emailNull}"></c:out>
						<c:out value="${error.emailExist}"></c:out>
					<p>
				</div>
				<div>
					<span class="word">Password*</span><br> <input type="password"
						name="password">
					<p class="error">
						<c:out value="${error.passwords}"></c:out>
					<p>
				</div>
				<input type="submit" value="Register">
			</form>
		</div>
	</div>
</body>
</html>


