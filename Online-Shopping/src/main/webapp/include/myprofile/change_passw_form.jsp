<h2 class="">Change Your Password</h2>
<p>
	<c:out value="${param.message}"></c:out>
</p>
<p>
	<c:out value="${error.newPassword}"></c:out>
</p>
<p>
	<c:out value="${error.retypePassword}"></c:out>
</p>
<p>
	<c:out value="${error.oldPassword}"></c:out>
</p>
<p>
	<c:out value="${error.notMatch}"></c:out>
</p>
<p>
	<c:out value="${error.incorrectPassword}"></c:out>
</p>

<form class="" action="<c:url value='/profile'/>" method="post">
	<label for="">Old Password</label><br> <input type="password"
		name="oldPassword" value="" autofocus><br> <label for="">New
		Password</label><br> <input type="password" name="newPassword" value=""><br>
	<label for="">Re-Type New Password</label><br> <input
		type="password" name="retypePassword" value=""><br> <input
		type="hidden" name="action" value="changePassword"> <input
		type="submit" name="" value="SAVE">

</form>