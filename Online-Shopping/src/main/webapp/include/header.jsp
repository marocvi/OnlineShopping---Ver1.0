<%@page import="com.hai.util.Currency"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Header -->
<div class="header">
	<div class="header-top">
		<div class="container">
			<div class="header-top-in">
				<div class="logo">
					<a href='<c:url value="/home"></c:url>'><i></i></a>
				</div>
				<div class="header-in">

					<ul class="icon1 sub-icon1">
						<li><a
							href='<c:url value="/wishlist?action=view&page=1"></c:url>'>WISH
								LIST (<span id="numberOfItemsInWishlist"> <c:out
										value="${fn:length(listOfWishlistItems)}"></c:out>
							</span>)
						</a></li>
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


						<li><a href='<c:url value="/cart?action=viewCart"></c:url>'>
								SHOPPING CART</a></li>
						<li><div class="cart">
								<a href="#" class="cart-in" onmouseover="scrollCart()"> </a> <span
									id="numberOfItems">${fn:length(sessionScope.cart.cartDetails)}</span>
							</div>
							<ul class="sub-icon1 list">
								<h3>Recently added items</h3>
								<div id="shopping_cart">
									<c:forEach var="item" items="${sessionScope.cart.cartDetails}">
										<div class="cart_box" id="item_${item.product.id}">
											<div class="message">
												<div class="list_img">
													<img src="images/product/${item.product.profileImage}.jpg"
														class="img-responsive" alt="">
												</div>
												<div class="list_desc">
													<h4>
														<a
															href='<c:url value="/product?action=detail&product_id=${item.product.id}"></c:url>'>${item.product.name}</a>
													</h4>
													<p style="display: inline;"
														id="itemAmount_${item.product.id}">${item.amount}</p>
													x<span class="actual"> <fmt:formatNumber
															type="number" maxFractionDigits="2"
															value=" ${item.money}" /></span>
												</div>
												<div class="clearfix"></div>
											</div>
										</div>
									</c:forEach>
								</div>
								<div class="total">
									<div class="total_left">CartSubtotal :</div>
									<div class="total_right" id="priceTotal">
										${currency}
										<c:choose>
											<c:when test="${cart==null}">0.0</c:when>
											<c:otherwise>
											${cart.moneyTotal*rate}
										</c:otherwise>
										</c:choose>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="login_buttons">
									<div class="check_button">
										<a href='<c:url value="/cart?action=viewCart"></c:url>'>Check
											out</a>
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
					<li class="active"><a href='<c:url value="/home"></c:url>'><i>
						</i>Desktops</a></li>
					<c:forEach var="category" items="${sessionScope.categories}">
						<c:if test="${fn:length(category.subCategories)>0}">
							
						<li><a>${category.name}</a>
							<ul class="drop">
								<c:forEach var="subCategory" items="${category.subCategories}">
									<li><a
										href='<c:url value="/product?action=view&subcategoryid=${subCategory.id}&page=1"></c:url>'>
											${subCategory.name}</a></li>
								</c:forEach>
							</ul></li>
						</c:if>
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
						<li><a href="https://www.facebook.com/hai.mabu.7"
							target="blank"><i class="facebook"> </i></a></li>
						<li><a href="https://twitter.com/?lang=en" target="blank"><i
								class="twitter"> </i></a></li>
						<li><a href="https://www.skype.com/en/" target="blank"><i
								class="skype"> </i></a></li>
					</ul>
					<c:set var="currencyValues" value="<%=Currency.values()%>"></c:set>
					<div class="down-top">
						<select class="in-drop" id="currency" onchange="changeCurrency()">
							<c:forEach var="currencyValue" items="${currencyValues}">
								<option value="${currencyValue}" class="in-of">${currencyValue}</option>
							</c:forEach>
						</select>
					</div>

					<!-- Script for sent send Ajax to server -->
					<c:url var="changeCurrencyURL" value="/currency"></c:url>
					<script type="text/javascript">
						//Change the select to selectedCurrency
						var mySelect = document.getElementById("currency");
						for (var value, i = 0; value = mySelect.options[i]; i++) {
							if (value.value == "${selectedCurrency}") {
								mySelect.selectedIndex = i;
								break;
							}
						}
						function changeCurrency() {
							var selectedCurrency = document
									.getElementById("currency").value;
							var http = new XMLHttpRequest();
							http.onreadystatechange = function() {
								if (this.readyState == 4 && this.status == 200) {
									location.reload();
								}
							}
							http.open("POST",
									"${changeCurrencyURL}?selectedCurrency="
											+ selectedCurrency, true);
							http
									.setRequestHeader("Content-type",
											"application/x-www-form-urlencoded; charset=utf-8");
							http.send();

						}
					</script>
					<div class="search">
						<form>
							<input type="text" placeholder="Search" id="searchKey"
								onkeyup="search()"> <input type="submit" value="">
							<div class="result "">
								<ul id="resultList">

								</ul>

							</div>
						</form>
						<c:url var="searchURL" value="/searching"></c:url>
						<c:url var="viewDetailURL" value="/product?action=detail"></c:url>
						<script type="text/javascript">
							
							function search() {
								//delete previous result
								var ul = document.getElementById("resultList");
								while(ul.firstChild){
									ul.removeChild(ul.firstChild);
								}
								
								var searchKey = document
										.getElementById("searchKey").value;
								if (searchKey != "") {
									var http = new XMLHttpRequest();
									http.onreadystatechange = function() {
										if (this.readyState == 4
												&& this.status == 200) {
											addResultToPage(this);
										}
									}
									http.open("POST", "${searchURL}?searchKey="
											+ searchKey, true);
									http
											.setRequestHeader("Content-type",
													"application/x-www-form-urlencoded; charset=utf-8");
									http.send();
								};

							}
							function addResultToPage(response) {
								var json = JSON.parse(response.responseText);
								var products=json.product;
								var ul = document.getElementById("resultList");
								//Limit result to 4
								var length =0;
								if(products.length>4){
									length = 4;
								}
								else length = products.length
								for(var product, i=0;i<length;i++){
									product = products[i];
									//add product to result
									//create img
									var img = document.createElement("img");
									var srcAttr = document.createAttribute("src");
									srcAttr.value = "images/product/"+product.image+".jpg";
									img.setAttributeNode(srcAttr);
									//create p
									var para1 = document.createElement("p");
									para1.appendChild(img);
									//create strong
									var strong = document.createElement("strong");
									var strongTextNode = document.createTextNode("${currency}"+ (product.price*${rate}).toFixed(2));
									strong.appendChild(strongTextNode);
									//create p
									var para2 = document.createElement("p");
									para2.appendChild(strong);
									//create h3
									var head3 = document.createElement("h3");
									var head3TextNode = document.createTextNode(product.name);
									head3.appendChild(head3TextNode);
									//create div
									var div = document.createElement("div");
									div.appendChild(head3);
									div.appendChild(para2);
									//create a
									var anchor = document.createElement("a");
									var hrefAttr = document.createAttribute("href");
									var classAttr = document.createAttribute("class");
									classAttr.value = "clearfix";
									hrefAttr.value="${viewDetailURL}&product_id="+product.id;
									anchor.setAttributeNode(hrefAttr);
									anchor.setAttributeNode(classAttr);
									anchor.appendChild(div);
									anchor.appendChild(para1);
									//create li
									var list = document.createElement("li");
									list.appendChild(anchor);
									//append li to ul
									ul.appendChild(list);
									
								}
							}
							
						</script>
					</div>

					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>