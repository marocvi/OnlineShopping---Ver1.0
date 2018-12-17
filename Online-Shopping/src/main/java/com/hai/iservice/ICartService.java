package com.hai.iservice;

import java.util.List;

import com.hai.model.Cart;
import com.hai.model.CartDetail;

public interface ICartService {

	/**
	 * Update the cart and the cart_detail in DB
	 * @param cart
	 */
	public void update(Cart cart);
	
	public double getMoneyTotal(List<CartDetail> listOfItems);
	
}
