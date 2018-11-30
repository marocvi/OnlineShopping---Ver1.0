package com.hai.idao;

import java.util.List;

import com.hai.model.Price;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface IPriceDAO {
	
	public boolean save(Price price);

	public boolean update(Price price);

	public boolean delete(Price price);

	public boolean delete(Integer priceID);

	public Price findById(Integer priceID);

	public List<Price> findAll();
	
	public List<Price> findByProperty(String name, Object proValue);
}
