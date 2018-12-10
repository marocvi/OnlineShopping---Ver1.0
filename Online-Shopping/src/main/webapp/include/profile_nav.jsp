<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Profile</title>
<link rel="stylesheet" href="css/mystyle.css">
<style type="text/css">
nav {
	width: 300px;
	height: 1000px;
	margin-top: 50px;
	margin-bottom: 100px;
	padding-top: 50px;
	border-right: 1px solid #fb5e33;
	background-color: #fbf2d3;
	float: left;
}

nav img {
	height: 200px;
	width: 200px;
	margin-left: 50px;
	margin-top: 20px;
	margin-right: 50px;
	border-radius: 50%;
	border: 3px white solid;
	margin-right: 50px;
}

nav h1 {
	text-align: center;
	margin-top: 20px;
	font-size: 20px;
}

nav ul {
	font-size: 15px;
	list-style-type: none;
	margin-top: 100px;
}

nav ul li {
	text-decoration: none;
	margin-top: 10px;
	text-align: center;
}

nav ul li a {
	text-decoration: none;
	color: #4f9386;
	font: Sans-serif;
	font-size: 15px;
}

nav ul li a:hover, a:active {
	background-color: #fb5e33;
	color: white;
	padding: 5px 15px;
	text-align: center;
	text-decoration: none;
	border-radius: 20px;
}

nav ul li a:focus {
	text-decoration: none;
	color: #4f9386;
}

section {
	float: left;
	width: 70%;
	height: 1000px;
	margin-top: 50px;
	margin-bottom: 100px;
	position: relative;
	left: 50px;
}

section h2 {
	font-size: 20px;
}

section form {
	margin-top: 50px;
}

section label {
	text-align: left;
	font: sans-serif;
	font-weight: normal;
	font-size: 15px;
}

section input[type=text] {
	width: 80%;
	height: 50px;
	margin-top: 5px;
	margin-bottom: 15px;
	box-sizing: border-box;
	border: 1px #afb4bc solid;
	border-radius: 10px;
	padding-left: 7px;
	font-size: 15px;
}

section input[type=text]:focus {
	outline: none;
	cursor: auto;
}
section input[type=password] {
	width: 80%;
	height: 50px;
	margin-top: 5px;
	margin-bottom: 15px;
	box-sizing: border-box;
	border: 1px #afb4bc solid;
	border-radius: 10px;
	padding-left: 7px;
	font-size: 15px;
}

section input[type=password]:focus {
	outline: none;
	cursor: auto;
}

section input[type=submit] {
	margin-top: 30px;
	background-color: #FB5E33;
	border: none;
	color: white;
	text-decoration: none;
	text-align: center;
	width: 100px;
	height: 35px;
	border-radius: 10px;
	font-weight: bold;
	-moz-transition: all 0.3s;
	-webkit-transition: all 0.3s;
}


section input[type=submit]:hover {
	background-color: #34f9f3;
	cursor: pointer;
}

section input[type=submit]:focus {
	outline: none;
}

.error{
	color:red;
	margin-top: 30px;
}


.msg{
	color: green;
	margin-top: 30px;
}

</style>
</head>
<body>
	<div class="container">
		<nav>
			<img src="images/avatar.jpg" alt="">
			<h1>Hai Mabu</h1>
			<div>
				<div class="">
					<ul>
						<li><a
							href='<c:url value ="/profile?action=view&choice=profile"></c:url>'>The
								profile</a></li>
						<li><a href='<c:url value ="#"></c:url>'>Order History</a></li>
						<li><a
							href='<c:url value ="/profile?action=view&choice=cpassword"></c:url>'>Change
								The Password</a></li>
						<li><a href=<c:url value ="/profile?action=view&choice=payment"></c:url>>Payment
								Method</a></li>
						<li><a href=<c:url value ="/profile?action=view&choice=close"></c:url>>Close
								Account</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<%--Depending on section value , we chose proper jsp from myprofile --%>
		<section>
			<c:if test="${param.choice eq 'profile'}">
				<%@ include file="myprofile/profile_form.jsp"%>
			</c:if>
			<c:if test="${param.choice eq 'orders'}">
				<%@ include file="myprofile/order_list.jsp"%>
			</c:if>
			<c:if test="${param.choice eq 'cpassword'}">
				<%@ include file="myprofile/change_passw_form.jsp"%>
			</c:if>
			<c:if test="${param.choice eq 'payment'}">
				<%@ include file="myprofile/payment_method.jsp"%>
			</c:if>
			<c:if test="${param.choice eq 'close'}">
				<%@ include file="myprofile/close_account.jsp"%>
			</c:if>
		</section>
	</div>
</body>
</html>
