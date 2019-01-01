<%@page import="org.hibernate.SessionFactory"%>
<%@page import="com.hai.service.SpecialOfferServiceImpl"%>
<%@page import="com.hai.iservice.ISpecialOfferService"%>
<%@page import="com.hai.model.SpecialOffer"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	 ISpecialOfferService specialOfferService = new SpecialOfferServiceImpl((SessionFactory)getServletContext().getAttribute("sessionFactory"));
	//banner
	List<SpecialOffer> offers = specialOfferService.getListOfValidSpecialOffers();
	request.getSession().setAttribute("offers", offers);
%>
<div class="banner-mat">
	<div class="container">
		<div class="banner">

			<!-- Slideshow 4 -->
			<div class="slider">
				<ul class="rslides" id="slider1">
					<c:forEach var="offer" items="${offers}">
						<li><a
							href='<c:url value="/product?action=detail&product_id=${offer.product.id}" ></c:url>'><img
								src="images/${offer.image}.png" alt=""></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- //slider-->
	</div>
</div>