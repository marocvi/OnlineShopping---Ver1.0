<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--A Design by W3layouts 
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>Bonfire a Ecommerce Category Flat Bootstarp Responsive
	Website Template | Single :: w3layouts</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css;"
	media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Bonfire Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
	
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 





</script>
<!--fonts-->
<link
	href='http://fonts.googleapis.com/css?family=Exo:100,200,300,400,500,600,700,800,900'
	rel='stylesheet' type='text/css'>
<!--//fonts-->
<!--  
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
-->
<script type="text/javascript" src="js/myScript.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<link rel="stylesheet" href="css/etalage.css">
<script src="js/jquery.etalage.min.js"></script>
<script type="text/javascript" src="js/myScript.js"></script>
<script>
	jQuery(document)
			.ready(
					function($) {

						$('#etalage')
								.etalage(
										{
											thumb_image_width : 300,
											thumb_image_height : 400,
											source_image_width : 900,
											source_image_height : 1200,
											show_hint : true,
											click_callback : function(
													image_anchor, instance_id) {
												alert('Callback example:\nYou clicked on an image with the anchor: "'
														+ image_anchor
														+ '"\n(in Etalage instance: "'
														+ instance_id + '")');
											}
										});

					});
</script>
<script>
	$(document).ready(function(c) {
		$('.alert-close').on('click', function(c) {
			$('.message').fadeOut('slow', function(c) {
				$('.message').remove();
			});
		});
	});
</script>
<script>
	$(document).ready(function(c) {
		$('.alert-close1').on('click', function(c) {
			$('.message1').fadeOut('slow', function(c) {
				$('.message1').remove();
			});
		});
	});
</script>
<style type="text/css">
#carting {
	background-color: #24e5df;
	outline: none;
	border: none;
	color: white;
	font-family: sans-serif;
	position: relative;
	margin-top: 5px;
	margin-left: 320px;
	height: 35px;
	border-radius: 10px;
}

#carting:hover {
	background-color: #FB5E33;
}

textarea {
	margin-top: 40px;
	resize: vertical;
	border-radius: 10px;
	width: 80%;
}

textarea:focus {
	outline: none;
}

button.submit {
	background-color: #1fb7b2;
	color: white;
	width: 80px;
	height: 30px;
	border-radius: 5px;
	border: none;
	margin: 20px auto;
}

button.submit:focus {
	outline: none;
}

.comment {
	margin-top: 30px;
}

.comment ul {
	list-style-type: none;
	width: 80%
}

.comment ul i {
	color: #a1a7b3;
	font-size: 12px;
}

.comment ul li {
	padding-top: 20px;
}

.comment h4 {
	max-width: 200px;
	overflow: hidden;
	padding-top: 10px;
	border-top: 1px #FB5E33 solid;
	margin-top: 20px;
}

img.fashion {
	width: 98px;
	height: 98px;
}

a.cart-to {
	margin-left: 10px;
}

#addcart, #addToWishlist, .removeFromWishlist, .addToWishlist:hover {
	cursor: pointer;
}
</style>
</head>


<!-- Template go here -->
<body>

	<!-- Add header -->
	<%@ include file="include/header.jsp"%>

	<!-- Product Detail -->
	<%@ include file="include/product_detail_content.jsp"%>

	<!-- Add footer here -->
	<%@ include file="include/footer.jsp"%>

	<!-- Custome script -->
	<%--This is js script for update commnet to Server using AJAX--%>
	<c:url var="myUrl" value="/product">
		<c:param name="action" value="comment" />
		<c:param name="product_id" value="${product.id}"></c:param>
	</c:url>
	<script type="text/javascript">
	<!--Check if user login yet or your content is empty-->
		function submitComment() {
			var content = document.getElementById("content").value;
			var firstName = "${sessionScope.user.firstName}";
			var data = "content=" + content;
			if (content === "") {
				alert("Please enter your comment");
			} else if (firstName == "") {
				alert("Please login first!")
			} else {

				var http = new XMLHttpRequest();
				http.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						addComment(this);
					}
				}
				http.open("POST", "${myUrl}", true);
				http.setRequestHeader("Content-type",
						"application/x-www-form-urlencoded; charset=utf-8");
				http.send(data);
			}

		}
		function addComment(response) {
			var userNameNode = document.createElement("h4");
			var contentNode = document.createElement("li");
			var userName = document
					.createTextNode("${sessionScope.user.firstName}");
			var content = document.createTextNode(response.responseText);
			contentNode.appendChild(content);
			userNameNode.appendChild(userName);
			document.getElementById("commentList").prepend(contentNode);
			document.getElementById("commentList").prepend(userNameNode);
			//delete texare
			document.getElementById("content").value = "";
		}
	</script>
	<!--Add to cart-->
	<c:url var="cartUrl" value="/product">
		<c:param name="action" value="cart" />
	</c:url>
	<script type="text/javascript">
		function addToCart(productId) {
			//check if user set ammount want to buy else set amount to 1.
			var amount = document.getElementById("quantity").value;
			if (amount < 0) {
				alert("Please enter amount > 0");
			} else {

				var color = document.getElementById("color").value;
				var size = document.getElementById("size").value;
				var data = "size=" + size + "&color=" + color + "&amount="
						+ amount + "&product_id=" + productId;
				var http = new XMLHttpRequest();

				http.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
					}
				}
				http.open("POST", "${cartUrl}", true);
				http.setRequestHeader("Content-type",
						"application/x-www-form-urlencoded");
				http.send(data);
			}

		}
	</script>
	<!-- Add and Remove product to wishlist -->
	<c:url var="addToWishlistURL" value="/product?action=wishlist"></c:url>
	<c:url var="removeFromWishlistURL" value="/wishlist?action=remove"></c:url>
	<script type="text/javascript">
		function addToWishlist(productID) {
			var http = new XMLHttpRequest();
			http.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					addToWishlistChange(productID);
				}
			}
			http.open("POST", "${addToWishlistURL}&product_id=" + productID,
					true);
			http.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded; charset=utf-8");
			http.send();
		}
		function addToWishlistChange(productID) {
			//Add "remove wishlist" button
			var removeNode = document.createElement("a");
			var attrClass = document.createAttribute("class");
			attrClass.value = "hvr-shutter-in-vertical cart-to removeFromWishlist";
			var attrOnclick = document.createAttribute("onclick");
			attrOnclick.value = "removeFromWishlist("+productID+")";
			var nameOfButton = document.createTextNode("Remove From Wishlist");

			removeNode.setAttributeNode(attrClass);
			removeNode.setAttributeNode(attrOnclick);
			removeNode.append(nameOfButton);
			var functionButton = document.getElementById("functionButton");
			functionButton.append(removeNode);
			//disapear the " add to wishlist" button
			var buttonAdds = document.getElementsByClassName("addToWishlist");
			for(var i=0;i<buttonAdds.length;i++){
				buttonAdds[i].style.display="none";
			}

			//Change number of items in wishlist
			var numberOfWishlistItems = parseInt(document
					.getElementById("numberOfItemsInWishlist").innerHTML) + 1;
			document.getElementById("numberOfItemsInWishlist").innerHTML = " "
					+ numberOfWishlistItems + " ";

		}
		function removeFromWishlist(productID) {
			var http = new XMLHttpRequest();
			http.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					removeFromWishlistChange(productID);
				}
			}
			http.open("POST", "${removeFromWishlistURL}&product_id=" + productID,
					true);
			http.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded; charset=utf-8");
			http.send();
		}
		function removeFromWishlistChange(productID) {
			//Add "remove wishlist" button
			var addNode = document.createElement("a");
			var attrClass = document.createAttribute("class");
			attrClass.value = "hvr-shutter-in-vertical cart-to addToWishlist";
			
			var attrOnclick = document.createAttribute("onclick");
			attrOnclick.value = "addToWishlist("+productID+")";
			var nameOfButton = document.createTextNode("Add To Wishlist");

			addNode.setAttributeNode(attrClass);
			addNode.setAttributeNode(attrOnclick);
			addNode.append(nameOfButton);
			var functionButton = document.getElementById("functionButton");
			functionButton.append(addNode);
			//disapear the " add to wishlist" button
			var buttonRemoves = document.getElementsByClassName("removeFromWishlist");
			for(var i=0;i<buttonRemoves.length;i++){
				buttonRemoves[i].style.display="none";
			}

			//Change number of items in wishlist
			var numberOfWishlistItems = parseInt(document
					.getElementById("numberOfItemsInWishlist").innerHTML) - 1;
			document.getElementById("numberOfItemsInWishlist").innerHTML = " "
					+ numberOfWishlistItems + " ";
		}
	</script>
</body>
</html>