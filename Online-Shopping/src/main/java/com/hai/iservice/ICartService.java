package com.hai.iservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hai.model.Cart;
import com.hai.model.CartDetail;
import com.hai.model.Users;

public interface ICartService {

	public void save(Cart cart);
	
	/**
	 * Update the cart and the cart_detail in DB
	 * @param cart
	 */
	public void update(Cart cart);
	
	public double getMoneyTotal(List<CartDetail> listOfItems);
	
	public void updateSessionCart(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * Remove item from a cart
	 * @param cart
	 * @param productID
	 */
	public void removeCartDetail(Cart cart, int productID);
	
	/**
	 * Subtract number of amount of an item from cart.
	 * @param cart
	 * @param productID
	 * @param amount
	 */
	public void subtractItemAmount(Cart cart, int productID, int amount);
	
	
	public Cart getCartById(int cartID);
	
}
