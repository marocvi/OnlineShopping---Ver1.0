package com.hai.idao;

import java.util.List;

import com.hai.model.Order;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface IOrderDAO {
	
	public boolean save(Order order);

	public boolean update(Order order);

	public boolean delete(Order order);

	public boolean delete(Integer orderID);

	public Order findById(Integer orderID);

	public List<Order> findAll();
	
	public List<Order> findByProperty(String name, Object proValue);
}
