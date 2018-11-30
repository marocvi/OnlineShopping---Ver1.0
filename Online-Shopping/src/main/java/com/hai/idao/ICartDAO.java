package com.hai.idao;

import java.util.List;

import com.hai.model.Cart;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface ICartDAO {
	
	public boolean save(Cart cart);

	public boolean update(Cart cart);

	public boolean delete(Cart cart);

	public boolean delete(Integer cartID);

	public Cart findById(Integer cartID);

	public List<Cart> findAll();
	
	public List<Cart> findByProperty(String name, Object proValue);
}
