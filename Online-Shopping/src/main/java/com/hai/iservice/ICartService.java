package com.hai.iservice;

import com.hai.model.Cart;

public interface ICartService {

	/**
	 * Update the cart and the cart_detail in DB
	 * @param cart
	 */
	public void update(Cart cart);
	
}
