package com.hai.iservice;

import com.hai.model.Wishlist;

public interface IWishListService {
	/**
	 * Update wishlist to the DB 
	 * @param wishlist
	 */
	public void update(Wishlist wishlist);

}
