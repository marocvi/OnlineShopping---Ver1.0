package com.hai.idao;

import java.util.List;

import com.hai.model.Product;
/**
 * Interface including CRUD methods.
 * @author Mai_Van_Hai
 * @version 1.0
 * @since 2018-10-20
 */
public interface IProductDAO {
	
	public boolean save(Product product);

	public boolean update(Product product);

	public boolean delete(Product product);

	public boolean delete(Integer productID);

	public Product findById(Integer productID);

	public List<Product> findAll();
	
	public List<Product> findByProperty(String name, Object proValue);
}
