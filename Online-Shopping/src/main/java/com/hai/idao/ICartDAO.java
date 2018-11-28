package com.hai.idao;

import java.util.List;

import com.hai.model.Cart;
/**
 * This is interface specifying how Cart interact with database
 * @author Mai_Van_Hai
 * @version 1.0
 * @Since 2018-10-20
 */
public interface ICartDAO {

	/**
	 * This method for saveing a new Cart to db
	 * @param cart
	 */
	public void save(Cart cart);
	
	/**
	 * If there is something change to persist object, update it to db
	 * @param cart
	 */
	public void update(Cart cart);
	
	/**
	 * Delete Cart record in database
	 * @param cart
	 * @return if sucess return true else return false
	 */
	public boolean delete(Cart cart);
	
	/**
	 * Delete Cart record using identifier
	 * @param cartID This param determine identifier
	 * @return If sucess return true else return false
	 */
	public boolean  delete(Integer cartID);
	
	/**
	 *
	 * Find a record using identifier 
	 * @param cartID This is identifier
	 * @return The record that match to cartID
	 */
	public Cart findById(Integer cartID);
	
	/**
	 * Find All Cart record in db
	 * @return List of Cart
	 */
	public List<Cart> findAll();
	
	/**
	 * Find all Cart that fit to the conditions 
	 * @param name  The name of property
	 * @param proValue The value of property
	 * @return List of carts from database 
	 */
	public List<Cart>  findByProperty(String name, Object proValue);
	
	
	
}
