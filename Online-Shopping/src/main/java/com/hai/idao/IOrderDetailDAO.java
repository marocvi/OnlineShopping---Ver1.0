package com.hai.idao;

import java.util.List;

import com.hai.model.OrderDetail;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface IOrderDetailDAO {
	
	public boolean save(OrderDetail orderDetail);

	public boolean update(OrderDetail orderDetail);

	public boolean delete(OrderDetail orderDetail);

	public boolean delete(Integer orderDetailID);

	public OrderDetail findById(Integer orderDetailID);

	public List<OrderDetail> findAll();
	
	public List<OrderDetail> findByProperty(String name, Object proValue);
}
