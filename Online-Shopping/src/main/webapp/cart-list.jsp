<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<title>Online_Shoping</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
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
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
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
<!--slider-script-->
<script src="js/responsiveslides.min.js"></script>
<script>
	$(function() {
		$("#slider1").responsiveSlides({
			auto : true,
			speed : 500,
			namespace : "callbacks",
			pager : true,
		});
	});
</script>
<!--//slider-script-->
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
/*Cart- Items*/
#cart-summarize {
	float: right;
	box-sizing: border-box;
	width: 25%;
	margin-top: 50px;
	margin-bottom: 50px;
}

.cart-price {
	height: 110px;
}

.cart-price p {
	margin-bottom: 15px;
	/*text*/
	font-size: 20px;
}

.cart-price ul {
	list-style-type: none;
}

#moneyTotal {
	/*display*/
	/*text*/
	font-size: 40px;
	font-weight: bold;
}

#cart-summarize button {
	/*display*/
	box-sizing: border-box;
	width: 100%;
	height: 40px;
	border: none;
	border-radius: 2px;
	margin-top: 20px;
	/*text*/
	font-weight: bold;
	/*color*/
	background-color: #FB5E33;
	color: white;
}

#cart-summarize button:hover {
	/*color*/
	background-color: #aa2f11;
	color: white;
}

.list-items {
	float: left;
	width: 70%;
	margin-top: 50px;
	margin-bottom: 50px;
}

.list-items h3 {
	font-size: 20px;
}

#list-items {
	list-style-type: none;
	margin-top: 20px;
}

.cart-detail-box {
	border: 1px solid darkgray;
	height: 85px;
}

.cart-detail {
	margin: 10px;
	/*color*/
}

.cart-detail img {
	float: left;
	border: 1px solid grey;
	box-sizing: border-box;
	width: 65px;
	padding: 5px;
}

.cart-detail ul {
	float: left;
	margin-left: 50px;
	/*text*/
	list-style-type: none;
}

#price {
	float: left;
	margin-left: 50px;
}

#amount {
	float: right;
	margin-right: 50px;
	width: 90px;
	height: 25px;
}

#amount-minus {
	box-sizing: border-box;
	float: left;
	width: 25px;
	/*text*/
	text-align: center;
	font-weight: normal;
}

#amount span {
	box-sizing: border-box;
	border: 0.5px solid grey;
	float: left;
	width: 40px;
	height: 26px;
	padding-top: 4px;
	/*text*/
	text-align: center;
	font-size: 15px;
}

#amount-plus {
	box-sizing: border-box;
	float: right;
	width: 25px;
}

#close {
	float: right;
	width: 15px;
	height: 15px;
	/*text*/
	text-align: center;
	font-size: 10px;
	font-weight: bold;
}
</style>
</head>


<!-- Template go here -->
<body>

	<!-- Add header -->
	<%@ include file="include/header.jsp"%>

	<!-- List of cart items -->
	<%@ include file="include/cart_list_content.jsp"%>

	<!-- Add footer here -->
	<%@ include file="include/footer.jsp"%>
	<c:url var="removeURL" value="/cart?action=remove"></c:url>
	<script type="text/javascript">
	<!-- Disappeare item when click the close -->
		function deleteCart(productID) {
			var http = new XMLHttpRequest();
			http.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					changeFront(this);
				}
			};
			document.getElementById("cartDetail_" + productID).style.display = "none";
			document.getElementById("item_" + productID).style.display = "none";
			http.open("POST", "${removeURL}&product_id=" + productID, true);
			http.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded; charset=utf-8");
			http.send();
		}

		function changeFront(response) {
			var xmlData = (new DOMParser()).parseFromString(
					response.responseText, "text/xml");
			var cart = xmlData.getElementsByTagName("Cart")[0];
			var numberOfItems = cart.childNodes[0].innerHTML;
			var moneyTotal = cart.childNodes[1].innerHTML;
			//Change price and amount in content
			document.getElementById("numberOfCartDetails").innerHTML = numberOfItems;
			document.getElementById("moneyTotal").innerHTML = "$" + moneyTotal;
			//Change price and amount in header
			document.getElementById("numberOfItems").innerHTML = numberOfItems;
			document.getElementById("priceTotal").innerHTML = "$" + moneyTotal;

		}
	</script>
	<!-- Minus amount of item or add amount of item -->
	<c:url var="subtractURL" value="/cart?action=subtract"></c:url>
	<c:url var="plusURL" value="/cart?action=plus"></c:url>
	<script type="text/javascript">
		function minus(productID) {
			var amount = document.getElementById("cartDetailAmount_"
					+ productID).innerHTML;
			var http = new XMLHttpRequest();
			http.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					amountChangeInFrontBySubtract(this, productID);
				}
			}
			http.open("POST", "${subtractURL}&product_id=" + productID
					+ "&amount=" + amount, true);
			http.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded; charset=utf-8");
			http.send();

		}
		function add(productID) {
			var amount = document.getElementById("cartDetailAmount_"
					+ productID).innerHTML;
			var http = new XMLHttpRequest();
			http.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					amountChangeInFrontByPlus(this, productID);
				}
			}
			http.open("POST", "${plusURL}&product_id=" + productID + "&amount="
					+ amount, true);
			http.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded; charset=utf-8");
			http.send();

		}
		function amountChangeInFrontBySubtract(response, productID) {
		
			var amount = document.getElementById("cartDetailAmount_"
					+ productID).innerHTML;
			if (amount < 2) {
				document.getElementById("cartDetail_" + productID).style.display = "none";
				document.getElementById("item_" + productID).style.display = "none";
				changeFront(response);
				
			} else {
				amountChangeInFrontByPlus(response, productID);

			}

		};
		function amountChangeInFrontByPlus(response, productID) {
			var xmlData = (new DOMParser()).parseFromString(
					response.responseText, "text/xml");
			var cart = xmlData.getElementsByTagName("Cart")[0];
			var numberOfItems = cart.childNodes[0].innerHTML;
			var moneyTotal = cart.childNodes[1].innerHTML;
			var amount = cart.childNodes[2].innerHTML;
			//Change price and amount in content
			document.getElementById("numberOfCartDetails").innerHTML = numberOfItems;
			document.getElementById("moneyTotal").innerHTML = "$" + moneyTotal;
			document.getElementById("cartDetailAmount_" + productID).innerHTML = amount;
			//Change price and amount in header
			document.getElementById("numberOfItems").innerHTML = numberOfItems;
			document.getElementById("priceTotal").innerHTML = "$" + moneyTotal;
			document.getElementById("itemAmount_" + productID).innerHTML = amount;
		}
	</script>
</body>
</html>