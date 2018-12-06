<h2 class="">Your Account Information</h2>
  <form class="" action="<c:url value="/profile"></c:url>" method="post">
	
    <label for="firstName">First Name</label><br>
    <input type="text" name="firstName" value="${sessionScope.user.firstName}" ><br>
    <label for="lastName">Last Name</label><br>
    <input type="text" name="lastName" value="${sessionScope.user.lastName}"><br>
    <label for="phone">Phone Numbers</label><br>
    <input type="text" name="phone" value="${sessionScope.user.phone}"><br>
    <label for="address">Address</label><br>
    <input type="text" name="address" value="${sessionScope.user.address}"><br>
    <label for="email">Email</label><br>
    <input type="text" name="email" value="${sessionScope.user.email}" readonly="readonly"><br>
    <input type="hidden" name="action" value="updateProfile">
    <input type="submit" name="" value="SAVE">
</form>