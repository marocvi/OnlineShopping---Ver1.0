<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<div class="container">
	<div class="products">
		<h2 class=" products-in">WISH LIST</h2>

		<c:forEach var="product" items="${requestScope.listOfProducts}"
			varStatus="productIndex">
			<%--If surplus of productIdex divide by 4 equal 0 then add div with class="top-products"--%>
			<c:if test="${((productIndex.count-1)%4)==0}">
				<div class="top-products">
			</c:if>
			<div class="col-md-3 md-col">
				<div class="col-md">
					<a
						href='<c:url value ="/product?action=detail&product_id=${product.id}"></c:url>'
						class="compare-in"><img
						src="images/product/${product.profileImage}.jpg" alt="" /></a>

						<div class="top-content">
							<h5>
								<a href="product_detail.jsp">${product.name}</a>
							</h5>
							<div class="white">
								<a href='<c:url value ="/product?action=detail&product_id=${product.id}"></c:url>'
									class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">ADD
									TO CART</a>
								<p class="dollar">
									<c:forEach var="price" items="${product.prices}">
										<c:choose>
											<c:when
												test="${requestScope.today >= price.startDate.time && requestScope.today<= price.endDate.time}">
												<span class="in-dollar">${currency}</span>
												<span> <fmt:formatNumber type="number"
														maxFractionDigits="2" value="${price.unitPrice*rate}" /></span>
											</c:when>
											<%-- There is no price comfort to condition --%>
											<c:otherwise>
												<span class="in-dollar">$</span>
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
			<c:if test="${((productIndex.count)%4)==0}">
				<div class="clearfix"></div>
			
	</div>
	</c:if>

	</c:forEach>
	<c:if
		test="${requestScope.numberOfProducts <12 && requestScope.numberOfProducts%4 !=0}">
		<div class="clearfix"></div>
</div>
</c:if>

<ul class="start">
	<%
		Integer numberOfPages = (int) request.getAttribute("numberOfPages");
		Integer selectedPage = Integer.parseInt(request.getParameter("page"));
	%>
	<%--Setup perious button --%>
	<c:choose>
		<c:when test="${param.page>1}">
			<li><a
				href='<c:url value="/wishlist?action=view&page=${param.page-1}"/>'><i></i></a></li>
		</c:when>
		<c:otherwise>
			<li><a href='' class="disabled"><i></i></a></li>

		</c:otherwise>

	</c:choose>
	<%--Set up number of Page --%>
	<c:choose>
		<%-- when the page greater than 7 --%>
		<c:when test="${requestScope.numberOfPages>7}">
			<li class="arrow" id="page1"><a
				href='<c:url value="/wishlist?action=view&page=1"/>'>1</a></li>
			<c:choose>
				<c:when test="${param.page <=3}">
					<li class="arrow" id="page2"><a
						href='<c:url value="/wishlist?action=view&page=2"/>'>2</a></li>
					<li class="arrow" id="page3"><a
						href='<c:url value="/wishlist?action=view&page=3"/>'>3</a></li>
					<li class="arrow" id="page4"><a
						href='<c:url value="/wishlist?action=view&page=4"/>'>4</a></li>
					<li class="arrow">....</li>
				</c:when>
				<c:when test="${param.page<=requestScope.numberOfPages-3}">
					<li class="arrow">....</li>
					<li class="arrow" id="page${param.page-1}"><a
						href='<c:url value="/wishlist?action=view&page=${param.page-1}"/>'>${param.page-1}</a>
					</li>
					<li class="arrow" id="page${param.page}"><a
						href='<c:url value="/wishlist?action=view&page=${param.page}"/>'>${param.page}</a>
					</li>
					<li class="arrow" id="page${param.page+1}"><a
						href='<c:url value="/wishlist?action=view&page=${param.page+1}"/>'>${param.page+1}</a>
					</li>
					<li class="arrow">....</li>
				</c:when>
				<c:otherwise>
					<li class="arrow">....</li>
					<li class="arrow" id="page${requestScope.numberOfPages-3}"><a
						href='<c:url value="/wishlist?action=view&page=${requestScope.numberOfPages-3}"/>'>${requestScope.numberOfPages-3}</a>
					</li>
					<li class="arrow" id="page${requestScope.numberOfPages-2}"><a
						href='<c:url value="/wishlist?action=view&page=${requestScope.numberOfPages-2}"/>'>${requestScope.numberOfPages-2}</a>
					</li>
					<li class="arrow" id="page${requestScope.numberOfPages-1}"><a
						href='<c:url value="/wishlist?action=view&page=${requestScope.numberOfPages-1}"/>'>${requestScope.numberOfPages-1}</a>
					</li>
				</c:otherwise>
			</c:choose>
			<li class="arrow" id="page${requestScope.numberOfPages}"><a
				href='<c:url value="/wishlist?action=view&page=${requestScope.numberOfPages}"/>'>${requestScope.numberOfPages}</a>
			</li>
		</c:when>
		<%--when page is less than 7 --%>
		<c:otherwise>
			<%
				for (int i = 1; i <= numberOfPages; i++) {
			%>
			<li class="arrow " id="page"><a
				href='<c:url value="/wishlist?action=view&page="/><%=i%>'><%=i%></a></li>
			<%
				}
			%>
		</c:otherwise>
	</c:choose>
	<%-- Set up next button --%>
	<c:choose>
		<c:when test="${param.page<requestScope.numberOfPages}">
			<li><a
				href='<c:url value="/wishlist?action=view&page="/><%=selectedPage + 1%>'><i
					class="next"></i></a></li>
		</c:when>
		<c:otherwise>
			<li><a href='' class="disabled"><i class="next"></i></a></li>
		</c:otherwise>
	</c:choose>
</ul>
</div>
</div>
