<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container">
	<aside id="cart-summarize">
		<div class="cart-price">
			<div>
				<p>Total:</p>
				<ul>
					<li><span id="moneyTotal">$
					 <c:if test="${cart==null}">0.0</c:if>${cart.moneyTotal} </span></li>
				</ul>
			</div>
		</div>
		<div id="checkout">
			<button>Check out</button>
		</div>
	</aside>
	<section class="list-items">
		<h3>
			<span id="numberOfCartDetails">${fn:length(sessionScope.cart.cartDetails)}</span>
			Item in Cart
		</h3>
		<ul id="list-items">
			<c:forEach var="cartDetail" items="${sessionScope.cart.cartDetails}">
				<li id="cartDetail_${cartDetail.product.id}">
					<div class="cart-detail-box">
						<div class="cart-detail clearfix">
							<a
								href='<c:url value="/product?action=detail&product_id=${cartDetail.product.id}"></c:url>'><img
								src="images/${cartDetail.product.profileImage}.jpg"></a>
							<ul id="properties">
								<li>Name: <span
									style="font-weight: bold; margin-left: 5px; margin-bottom: 5px;">${cartDetail.product.name}</span></li>
								<li>Color: <span
									style="font-weight: bold; margin-left: 5px; margin-bottom: 5px;">${cartDetail.color}</span></li>
								<li>Size: <span
									style="font-weight: bold; margin-left: 5px; margin-bottom: 5px;">${cartDetail.size}</span></li>
							</ul>
							<p id="price">
								Unit Price: <span
									style="font-weight: bold; margin-left: 5px; margin-bottom: 5px;">$${cartDetail.money}</span>
							</p>
							<span id="close" onclick="deleteCart(${cartDetail.product.id})">x</span>
							<p id="amount">
								<button id="amount-minus" onclick="minus(${cartDetail.product.id})">-</button>
								<span  id="cartDetailAmount_${cartDetail.product.id}">${cartDetail.amount}</span>
								
								<button class="amount-plus" onclick="add(${cartDetail.product.id})">+</button>
							</p>
						</div>
					</div>
					<div class="clearfix"></div>
				</li>
			</c:forEach>
		</ul>
	</section>


</div>