package com.hai.idao;

import java.util.List;

import com.hai.model.Cart;
/**
 * This is interface specify for manipulation with dabase
 * @author Mai_Van_Hai
 * @version 1.0
 * @Since 2018-10-20
 */
public interface ICartDAO {

	/**
	 * This method for saveing a new object to db
	 * @param cart
	 */
	public void save(Cart cart);
	
	/**
	 * If there is something change to persist object, update it to db
	 * @param cart
	 */
	public void update(Cart cart);
	
	/**
	 * Delete Cart record in db
	 * @param cart
	 */
	public void delete(Cart cart);
	
	/**
	 * Delete Cart record using identifier
	 * @param cartID This param determine identifier
	 */
	public void  delete(Integer cartID);
	
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
	 * @param name  The propertiy name
	 * @param proValue The property value
	 * @return List of Cart
	 */
	public List<Cart>  findByProperty(String name, Object proValue);
	
	
	
}
