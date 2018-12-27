package com.hai.iservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hai.model.Users;
import com.hai.model.Wishlist;

public interface IWishListService {
	/**
	 * Update wishlist to the DB 
	 * @param wishlist
	 */
	public void update(Wishlist wishlist);
	public List<Wishlist> getWishlistItemsByUser(Users user);
	public List<Wishlist> getProductByPage(int startPosition,int maxResult,Users user);
	public void save(Wishlist wishlist);
	public void updateSessionWishlist(HttpServletRequest request, HttpServletResponse response);
	public void deleteByIdAndUser(int productID,Users user);
	public void deleteById(int wishlistID);

}
