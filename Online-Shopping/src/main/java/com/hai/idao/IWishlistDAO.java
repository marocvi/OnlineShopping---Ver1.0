package com.hai.idao;

import java.util.List;

import com.hai.model.Wishlist;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface IWishlistDAO {
	
	public boolean save(Wishlist wishlist);

	public boolean update(Wishlist wishlist);

	public boolean delete(Wishlist wishlist);

	public boolean delete(Integer wishlistID);

	public Wishlist findById(Integer wishlistID);

	public List<Wishlist> findAll();
	
	public List<Wishlist> findByProperty(String name, Object proValue);
}
