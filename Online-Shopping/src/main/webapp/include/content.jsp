<%@page import="java.util.Date"%>
<%@page import="com.hai.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.hai.service.ProductServiceImpl"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<div class="container">
	<%
		SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
		if (request.getAttribute("greatestProducts") == null) {
			List<Product> greatestProducts = new ProductServiceImpl(sessionFactory)
					.getListOfGreatestProductsByBrand("Nokia");
			request.setAttribute("greatestProducts", greatestProducts);
		}
		if (request.getAttribute("newestProducts") == null) {
			List<Product> newestProducts = new ProductServiceImpl(sessionFactory)
					.getListOfNewestProductsByBrand("Nokia");
			request.setAttribute("newestProducts", newestProducts);
		}
		if (request.getAttribute("brand") == null) {
			List<String> brands = new ProductServiceImpl(sessionFactory).getListOfProductBrand();
			request.setAttribute("brands", brands);
		}
		if(request.getAttribute("today")==null){
			request.setAttribute("today", new Date().getTime());
		}
	%>
	<div class="content">
		<div class="content-top">
			<h3 class="future">THE MOST VIEWED PRODUCT</h3>
			<div class="content-top-in">
				<c:forEach var="product" items="${greatestProducts}">
					<div class="col-md-3 md-col">
						<div class="col-md">
							<a
								href='<c:url value ="/product?action=detail&product_id=${product.id}"></c:url>'><img
								src="images/product/${product.profileImage }.jpg" alt="" /> </a>
							<div class="top-content">
								<h5>${product.name}</h5>
								<div class="white">
									<a
										href='<c:url value ="/product?action=detail&product_id=${product.id}"></c:url>'
										class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">ADD
										TO CART</a>
									<p class="dollar">
										<c:forEach var="price" items="${product.prices}">
											<c:choose>
												<c:when
													test="${requestScope.today >= price.startDate.time && requestScope.today<= price.endDate.time}">
													<span class="in-dollar">${currency}</span>
													<span> <fmt:formatNumber type="number"
															maxFractionDigits="2" value="${price.unitPrice*rate}" />
													</span>
												</c:when>
												<%-- There is no price comfort to condition --%>
												<c:otherwise>
													<span class="in-dollar">${currency}</span>
													<span>00</span>
												</c:otherwise>

											</c:choose>
										</c:forEach>
									</p>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"></div>
			</div>
		</div>
		<!---->
		<div class="content-middle">
			<h3 class="future">BRANDS</h3>
			<div class="content-middle-in">
				<ul id="flexiselDemo1">
					<c:forEach var="brand" items="${brands}">
						<li><a href='<c:url value="home?brand=${brand}"></c:url>'><img
								src="images/brand/${brand}.png" style="width: 80px;" /></a></li>
					</c:forEach>

				</ul>
				<script type="text/javascript">
					$(window).load(function() {
						$("#flexiselDemo1").flexisel({
							visibleItems : 4,
							animationSpeed : 1000,
							autoPlay : true,
							autoPlaySpeed : 5000,
							pauseOnHover : true,
							enableResponsiveBreakpoints : true,
							responsiveBreakpoints : {
								portrait : {
									changePoint : 480,
									visibleItems : 1
								},
								landscape : {
									changePoint : 640,
									visibleItems : 2
								},
								tablet : {
									changePoint : 768,
									visibleItems : 3
								}
							}
						});

					});
				</script>
				<script type="text/javascript" src="js/jquery.flexisel.js"></script>

			</div>
		</div>
		<!---->
		<div class="content-bottom">
			<h3 class="future">LATEST</h3>
			<div class="content-bottom-in">
				<ul id="flexiselDemo2">
					<c:forEach var="product" items="${newestProducts}">
						<li><div class="col-md men">
								<a href="single.html" class="compare-in "><img
									src="images/product/${product.profileImage}.jpg" alt="" />
									<div class="top-content bag">
										<h5>${product.name}</h5>
										<div class="white">
											<a href="single.html"
												class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">ADD
												TO CART</a>
											<p class="dollar">
												<c:forEach var="price" items="${product.prices}">
													<c:choose>
														<c:when
															test="${requestScope.today >= price.startDate.time && requestScope.today<= price.endDate.time}">
															<span class="in-dollar">${currency}</span>
															<span> <fmt:formatNumber type="number"
																	maxFractionDigits="2" value="${price.unitPrice*rate}" />
															</span>
														</c:when>
														<%-- There is no price comfort to condition --%>
														<c:otherwise>
															<span class="in-dollar">${currency}</span>
															<span>00</span>
														</c:otherwise>

													</c:choose>
												</c:forEach>
											</p>
											<div class="clearfix"></div>
										</div>
									</div>
							</div></li>
					</c:forEach>


				</ul>
				<script type="text/javascript">
					$(window).load(function() {
						$("#flexiselDemo2").flexisel({
							visibleItems : 4,
							animationSpeed : 1000,
							autoPlay : true,
							autoPlaySpeed : 4000,
							pauseOnHover : true,
							enableResponsiveBreakpoints : true,
							responsiveBreakpoints : {
								portrait : {
									changePoint : 480,
									visibleItems : 1
								},
								landscape : {
									changePoint : 640,
									visibleItems : 2
								},
								tablet : {
									changePoint : 768,
									visibleItems : 3
								}
							}
						});

					});
				</script>
			</div>
		</div>

	</div>
</div>