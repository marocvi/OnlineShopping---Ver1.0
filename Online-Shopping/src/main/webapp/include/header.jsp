<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Header -->
<div class="header">
	<div class="header-top">
		<div class="container">
			<div class="header-top-in">
				<div class="logo">
					<a href='<c:url value="/home"></c:url>'><img src="images/logo.png" alt=" "></a>
					
				</div>
				<div class="header-in">

					<ul class="icon1 sub-icon1">
						<li><a href="wishlist.html">WISH LIST (0)</a></li>
						<c:choose>
						<%--If user already login--%>
							<c:when test="${sessionScope.user!=null}">
								<li><a href=" <c:url value= '/account?action=profile' /> ">MY
										ACCOUNT</a></li>
								<li><a href=" <c:url value= '/account?action=logout' /> ">LOGOUT</a></li>

							</c:when>
						<%-- If there is no user in session --%>
							<c:otherwise>
								<li><a href=" <c:url value= '/account?action=register' /> ">SIGN
										UP</a></li>
								<li><a href=" <c:url value= '/account?action=login' /> ">LOGIN</a></li>
							</c:otherwise>

						</c:choose>


						<li><a href="#"> SHOPPING CART</a></li>
						<li><a href="checkout.html">CHECKOUT</a></li>
						<li><div class="cart">
								<a href="#" class="cart-in"> </a> <span> 0</span>
							</div>
							<ul class="sub-icon1 list">
								<h3>Recently added items(2)</h3>
								<div class="shopping_cart">
									<div class="cart_box">
										<div class="message">
											<div class="alert-close"></div>
											<div class="list_img">
												<img src="images/14.jpg" class="img-responsive" alt="">
											</div>
											<div class="list_desc">
												<h4>
													<a href="#">velit esse molestie</a>
												</h4>
												1 x<span class="actual"> $12.00</span>
											</div>
											<div class="clearfix"></div>
										</div>
									</div>
									<div class="cart_box1">
										<div class="message1">
											<div class="alert-close1"></div>
											<div class="list_img">
												<img src="images/15.jpg" class="img-responsive" alt="">
											</div>
											<div class="list_desc">
												<h4>
													<a href="#">velit esse molestie</a>
												</h4>
												1 x<span class="actual"> $12.00</span>
											</div>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
								<div class="total">
									<div class="total_left">CartSubtotal :</div>
									<div class="total_right">$250.00</div>
									<div class="clearfix"></div>
								</div>
								<div class="login_buttons">
									<div class="check_button">
										<a href="checkout.html">Check out</a>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="clearfix"></div>
							</ul></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="header-bottom">
		<div class="container">
			<div class="h_menu4">
				<a class="toggleMenu" href="#">Menu</a>
				<ul class="nav">
					<li class="active"><a href='<c:url value="/home"></c:url>'><i> </i>Desktops</a></li>
					<c:forEach var="category" items="${sessionScope.categories}">
						<li><a href="#">${category.name}</a>
							<ul class="drop">
								<c:forEach var="subCategory" items="${category.subCategories}">
									<li><a href='<c:url value="/product?action=view&subcategoryid=${subCategory.id}&page=1"></c:url>'>
									${subCategory.name}</a></li>
								</c:forEach>
							</ul></li>
					</c:forEach>

				</ul>
				<script type="text/javascript" src="js/nav.js"></script>
			</div>
		</div>
	</div>
	<div class="header-bottom-in">
		<div class="container">
			<div class="header-bottom-on">
				<p class="wel">
					<c:choose>
						<c:when test="${sessionScope.user!=null}">
							<a href="#">Welcome <c:out
									value="${sessionScope.user.lastName}"></c:out></a>
						</c:when>
						<c:otherwise>
							<a href="#">Welcome Vistor</a>
						</c:otherwise>
					</c:choose>

				</p>
				<div class="header-can">
					<ul class="social-in">
						<li><a href="#"><i> </i></a></li>
						<li><a href="#"><i class="facebook"> </i></a></li>
						<li><a href="#"><i class="twitter"> </i></a></li>
						<li><a href="#"><i class="skype"> </i></a></li>
					</ul>
					<div class="down-top">
						<select class="in-drop">
							<option value="Dollars" class="in-of">Dollars</option>
							<option value="Euro" class="in-of">Euro</option>
							<option value="Yen" class="in-of">Yen</option>
						</select>
					</div>
					<div class="search">
						<form>
							<input type="text" value="Search" onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = '';}"> <input
								type="submit" value="">
						</form>
					</div>

					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>