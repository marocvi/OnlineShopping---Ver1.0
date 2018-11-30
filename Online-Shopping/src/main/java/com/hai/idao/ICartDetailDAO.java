package com.hai.idao;

import java.util.List;

import com.hai.model.CartDetail;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface ICartDetailDAO {
	
	public boolean save(CartDetail cartDetail);

	public boolean update(CartDetail cartDetail);

	public boolean delete(CartDetail cartDetail);

	public boolean delete(Integer cartDetailID);

	public CartDetail findById(Integer cartDetailID);

	public List<CartDetail> findAll();
	
	public List<CartDetail> findByProperty(String name, Object proValue);
}
