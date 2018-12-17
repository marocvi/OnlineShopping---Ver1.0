function scrollCart() {
			var numberOfItems = document.getElementById("numberOfItems").innerHTML;
			if(numberOfItems >3 ){
				//Add class shopping-cart
				document.getElementById("shopping_cart").classList.add("shopping_cart");
			}			
		}