<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"
	isELIgnored="false"%>
<div class="container">
	<div class="single">
		<div class="col-md-9 top-in-single">
			<div class="col-md-5 single-top">
				<ul id="etalage">
					<c:forEach var="image" items="${listOfImages}">
						<li><img class="etalage_thumb_image img-responsive"
							src="images/${image}.jpg" alt=""> <img
							class="etalage_source_image img-responsive"
							src="images/${image}.jpg" alt=""></li>
					</c:forEach>
				</ul>

			</div>
				<div class="col-md-7 single-top-in">
					<div class="single-para">
						<h4>${product.name}</h4>
						<div class="para-grid">
							<span class="add-to" id="price">$${price}</span> 
							<c:url value="/product" var="detailUrl">
								<c:param name="action" value="detail"></c:param>
								<c:param name="product_id" value="${param.product_id}"></c:param>
							</c:url>
							<a href="${detailUrl }" onclick="addToCart(${param.product_id})" class="hvr-shutter-in-vertical cart-to" id="addcart">Add to Cart</a>
							<a href="#" class="hvr-shutter-in-vertical cart-to" id ="addwishlist" >Whislist</a>
							<div class="clearfix"></div>
						</div>
						<h5 id="amount">${product.stock}</h5>
						<div class="available">
							<h6>Available Options :</h6>
							<ul>
								<li>Color: <select  id="color">
										<c:forEach var="color" items="${listOfColors}">
											<option>${color}</option>
										</c:forEach>
								</select>
								</li>
								<li>Size:<select id="size">
										<c:forEach var="size" items="${listOfSizes}">
											<option>${size}</option>
										</c:forEach>
								</select></li>
								<li>Quantity: <input type="number" id="quantity"
									style="width: 50px; height: 24px;">
								</li>
							</ul>
						</div>
						<p>${product.description}</p>

					</div>
				</div>
			<div class="clearfix"></div>
			<div class="content-top-in">
				<div class="comment">
					<textarea rows="5" cols="100"
						placeholder="Please enter your commnet..." id="content"></textarea>
					<br>
					<button type="submit" class="submit" onclick="submitComment()">Comment</button>
					<ul id="commentList">
						<c:forEach var="comment" items="${listOfComments}">
							<h4>${comment.userName}</h4>
							<li>${comment.content}</li>
						</c:forEach>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="col-md-3">
			<div class="single-bottom">
				<h4>SUBCATEGORIES</h4>

				<ul>
					<c:forEach var="subCategory" items="${listOfSubCategories}">
						<li><a href='<c:url value="/product?action=view&subcategoryid=${subCategory.id}&page=1"></c:url>'><i> </i>${subCategory.name}</a></li>
					</c:forEach>


				</ul>
			</div>
			<div class="single-bottom">
				<h4>RELATED PRODUCTS</h4>
				<c:forEach var="product" items="${listOfProducts}">

					<div class="product">
						<img class="img-responsive fashion"
							src="images/${product.profileImage}.jpg" alt="">
						<div class="grid-product">
							<a href='<c:url value="/product?action=detail&product_id=${product.id}"></c:url>' class="elit">${product.name}</a> <span
								class="price price-in"> $ <c:forEach var="price"
									items="${product.prices}">
									<c:choose>
										<c:when
											test="${requestScope.today >= price.startDate.time && requestScope.today<= price.endDate.time}">
											${price.unitPrice}"
										</c:when>
										<%-- There is no price comfort to condition --%>
										<c:otherwise>
											00
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</span>
						</div>
						<div class="clearfix"></div>
					</div>

				</c:forEach>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>